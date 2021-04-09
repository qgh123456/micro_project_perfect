package cc.mrbird.febs.common.selector;

import cc.mrbird.febs.common.config.MybatisPlusConfig;
import cc.mrbird.febs.common.config.ThreadPoolConfig;
import cc.mrbird.febs.common.configure.FebsAuthExceptionConfigure;
import cc.mrbird.febs.common.configure.FebsLettuceRedisConfigure;
import cc.mrbird.febs.common.configure.FebsOAuth2FeignConfigure;
import cc.mrbird.febs.common.configure.FebsServerProtectConfigure;
import cc.mrbird.febs.common.handler.CommonMetaObjectHandler;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/5 10:19
 */
public class FebsCloudApplicationSelector implements ImportSelector{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),
                FebsOAuth2FeignConfigure.class.getName(),
                FebsServerProtectConfigure.class.getName(),
                FebsLettuceRedisConfigure.class.getName(),
                // 线程池
                ThreadPoolConfig.class.getName(),
                // mybatisplus的相关配置
                MybatisPlusConfig.class.getName()
        };
    }

}
