package cc.mrbird.febs.common.aspectj;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.entity.FebsAuthUser;
import cc.mrbird.febs.common.entity.system.SysOperLog;
import cc.mrbird.febs.common.enums.BusinessStatus;
import cc.mrbird.febs.common.manager.AsyncManager;
import cc.mrbird.febs.common.utils.ServletUtils;
import cc.mrbird.febs.common.utils.SpringUtils;
import cc.mrbird.febs.common.utils.StringUtils;
import cc.mrbird.febs.common.utils.ip.IpUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 操作日志记录处理
 *
 * @author ruoyi
 */
//@Aspect
//@Component
@ConditionalOnBean(type = "TokenStore")
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    // 配置织入点
    @Pointcut("@annotation(cc.mrbird.febs.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String authorization = request.getHeader("Authorization");
            if(authorization.startsWith("bearer")){
                // 获取token
                String authKey = authorization.split(" ")[1];
                OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(authKey);
                Object principal = oAuth2Authentication.getPrincipal();
                if(principal instanceof FebsAuthUser){
                    // 获取当前用户
                    FebsAuthUser febsAuthUser = (FebsAuthUser)principal;
                    String username = febsAuthUser.getUsername();
                    // *========数据库日志=========*//
                    SysOperLog operLog = new SysOperLog();
                    operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
                    // 请求的地址
                    String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
                    operLog.setOperIp(ip);
                    // 返回参数
                    operLog.setJsonResult(JSON.toJSONString(jsonResult));
                    operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
                    operLog.setOperName(username);
                    if (e != null) {
                        operLog.setStatus(BusinessStatus.FAIL.ordinal());
                        operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
                        // 设置方法名称
                        String className = joinPoint.getTarget().getClass().getName();
                        String methodName = joinPoint.getSignature().getName();
                        operLog.setMethod(className + "." + methodName + "()");
                        // 设置请求方式
                        operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
                        // 设置请求方式
                        operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
                        // 处理设置注解上的参数
                        getControllerMethodDescription(joinPoint, controllerLog, operLog);
                    }
                    // 通过mq把日志发到队列中,实现解耦的作用
                    this.sendTopicMessage(operLog);
                }
            }
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }

    public String sendTopicMessage(SysOperLog operLog){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = JSON.toJSONString(operLog);
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = Maps.newHashMap();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        // 设置回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                // 发送短信或者邮件，记录到文件中
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }
        });

        rabbitTemplate.convertAndSend("topicOpLogExchange", "topic.opLog", manMap);
        return "ok";
    }
}
