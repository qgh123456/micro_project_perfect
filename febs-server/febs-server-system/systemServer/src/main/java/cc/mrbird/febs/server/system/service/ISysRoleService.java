package cc.mrbird.febs.server.system.service;


import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色信息表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
public interface ISysRoleService extends IService<SysRole> {


    List<SysRole> findAllRoles();

    Page<SysRole> findAllRolesByPage(QueryRequest queryRequest, String roleName);

    boolean checkRoleName(String roleName);

    void saveRole(SysRole sysRole);

    void updateRole(SysRole sysRole);

    void delRoles(String roleIds);
}

