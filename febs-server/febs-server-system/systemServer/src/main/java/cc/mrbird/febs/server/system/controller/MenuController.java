package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.route.VueRouter;
import cc.mrbird.febs.common.entity.system.Menu;
import cc.mrbird.febs.server.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/2/1 17:16
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/{username}")
    public Result getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> result = new HashMap<>();
        // 构建用户路由对象
        List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(username);
        // 获取用户权限信息
        Set<String> userPermissions = this.menuService.findUserPermissions(username);
        // 组装数据
        result.put("routes", userRouters);
        result.put("permissions", userPermissions);
        return Result.ok().data(result);
    }


}
