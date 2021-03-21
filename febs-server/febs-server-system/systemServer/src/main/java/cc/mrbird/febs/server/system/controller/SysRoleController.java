package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.server.system.service.ISysRoleService;
import cc.mrbird.febs.server.system.vo.SysRole;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Api(tags = "角色信息表 管理")
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/options")
    public Result options(){

        List<SysRole> allRoles = sysRoleService.findAllRoles();
        return Result.ok().data(allRoles);
    }




}
