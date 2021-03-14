package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.IUserRoleService;
import cc.mrbird.febs.server.system.mapper.UserRoleMapper;
import cc.mrbird.febs.server.system.vo.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/7 17:35
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService{

    @Override
    @Transactional
    public void deleteUserRolesByRoleId(String[] roleIds) {
        Arrays.stream(roleIds).forEach(id -> baseMapper.deleteByRoleId(Long.valueOf(id)));
    }

    @Override
    @Transactional
    public void deleteUserRolesByUserId(String[] userIds) {
        Arrays.stream(userIds).forEach(id -> baseMapper.deleteByUserId(Long.valueOf(id)));
    }
}
