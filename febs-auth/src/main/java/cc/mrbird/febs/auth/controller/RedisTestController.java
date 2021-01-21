package cc.mrbird.febs.auth.controller;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.Objects;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/13 10:50
 */
@RequestMapping("/redis")
@RestController
public class RedisTestController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/saveRedis")
    public FebsResponse saveRedis(@RequestBody Map<String,Object> map){
        String type = map.get("type").toString();
        switch (type){
            case "1":
                // String
                redisService.set(map.get("key").toString(),map.get("value"));
                break;
            case "2":
                break;
        }
        FebsResponse febsResponse = new FebsResponse();
        return febsResponse.message("成功");
    }

    @GetMapping("/getKey")
    public Result getKey(String key,String type){
        Object data = null;
        switch (type){
            case "1":
                // String
                data = redisService.get(key);
                break;
            case "2":
                break;
        }


        return Result.ok().data(data);
    }
    @GetMapping("/getKey2")
    public Result getKey2(String key){
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(key);
        Object principal = oAuth2Authentication.getPrincipal();

        return Result.ok().data(oAuth2Authentication);
    }

}
