package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.server.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



}
