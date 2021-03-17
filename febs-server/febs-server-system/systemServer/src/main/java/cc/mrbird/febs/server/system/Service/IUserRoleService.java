package cc.mrbird.febs.server.system.Service;

import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.server.system.vo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);

    List<SystemUser> queryPage(SystemUser systemUser);
}
