package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.server.system.service.ISysRoleService;
import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @GetMapping("/pageQuery")
    public Result options(QueryRequest queryRequest,String roleName){

        Page<SysRole> page = sysRoleService.findAllRolesByPage(queryRequest,roleName);
        return Result.ok().data(page);
    }

    @GetMapping("/check/{roleName}")
    public Result check(@PathVariable(value = "roleName") String roleName){

        boolean result = this.sysRoleService.checkRoleName(roleName);
        return Result.ok().data(result);
    }

    @PostMapping("/saveRole")
    public Result saveRole(SysRole sysRole){

        this.sysRoleService.saveRole(sysRole);
        return Result.ok().data("保存成功");
    }

    @PutMapping("/updateRole")
    public Result updateRole(SysRole sysRole){

        this.sysRoleService.updateRole(sysRole);
        return Result.ok().data("修改成功");
    }

}
