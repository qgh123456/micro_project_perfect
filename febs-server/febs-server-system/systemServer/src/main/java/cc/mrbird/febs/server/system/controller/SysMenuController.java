package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.server.system.service.ISysMenuService;
import cc.mrbird.febs.server.system.vo.MenuTree;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单权限表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Api(tags = "菜单权限表 管理")
@RestController
@RequestMapping("/sysmenu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping("getMenuTree")
    public Result getMenuTree(String menuName){

        Map<String, Object> menuTreeMap = sysMenuService.getMenuTree(menuName);
        return Result.ok().data(menuTreeMap);
    }



}
