package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.server.system.Service.ISysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色信息表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Api(tags = "角色信息表 管理")
@RestController
@RequestMapping("/sysrole")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;


}
