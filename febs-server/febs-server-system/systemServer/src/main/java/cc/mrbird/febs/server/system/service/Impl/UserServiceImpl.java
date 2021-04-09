package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.constant.FebsConstant;
import cc.mrbird.febs.common.entity.constant.StringConstant;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.server.system.mapper.UserDataPermissionMapper;
import cc.mrbird.febs.server.system.service.IUserRoleService;
import cc.mrbird.febs.server.system.service.IUserService;
import cc.mrbird.febs.server.system.mapper.UserMapper;
import cc.mrbird.febs.server.system.vo.UserDataPermission;
import cc.mrbird.febs.server.system.vo.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/7 17:27
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDataPermissionMapper userDataPermissionMapper;

    @Override
    public IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request) {
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
    @Transactional
    public void createUser(SystemUser user) {
        // 创建用户
        user.setCreateTime(new Date());
        user.setAvatar(SystemUser.DEFAULT_AVATAR);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
        // 保存用户数据权限关联关系
        String[] deptIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getDeptIds(), StringConstant.COMMA);
        setUserDataPermissions(user, deptIds);
    }

    @Override
    @Transactional
    public void updateUser(SystemUser user) {
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        user.setCreateTime(null);
        user.setModifyTime(new Date());
        updateById(user);


        String[] userIds = {String.valueOf(user.getUserId())};
        userRoleService.deleteUserRolesByUserId(userIds);
        String[] roles = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getRoleId(), StringConstant.COMMA);
        setUserRoles(user, roles);

        Arrays.stream(userIds).forEach(id -> userDataPermissionMapper.deleteById(Long.valueOf(id)));
        String[] deptIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getDeptIds(), StringConstant.COMMA);
        setUserDataPermissions(user, deptIds);
    }

    @Override
    @Transactional
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        removeByIds(list);
        // 删除用户角色
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    @Override
    public void queryPage(Page<SystemUser> pageParam, SystemUser systemUser) {

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("userName",systemUser.getUsername());
        queryMap.put("deptName",systemUser.getDeptName());
        Long pageNo = (pageParam.getCurrent() - 1) * pageParam.getSize();
        queryMap.put("pageNo",pageNo);
        queryMap.put("pageSize",pageParam.getSize());
        String createTimeFrom = Optional.ofNullable(systemUser.getCreateTimeFrom()).map(t -> t.toString() + " 00:00:00").orElse(null);
        String createTimeTo = Optional.ofNullable(systemUser.getCreateTimeTo()).map(t -> t.toString() + " 59:59:59").orElse(null);
        queryMap.put("createTimeFrom",createTimeFrom);
        queryMap.put("createTimeTo",createTimeTo);
        queryMap.put("field",systemUser.getField());
        queryMap.put("order",systemUser.getOrder());


        // 根据条件查询
        List<SystemUser> systemUsers = userMapper.queryPage(queryMap);
        // 获取总数
        Long count = userMapper.countByQueryMap(queryMap);
        pageParam.setRecords(systemUsers);
        pageParam.setTotal(count);

    }

    @Override
    public void passwordReset(String[] usernameList) {

        this.userMapper.batchUptPasswordReset(usernameList);
    }

    @Override
    public IPage<SystemUser> findUserDetailList(SystemUser user, QueryRequest queryRequest) {

        Page<SystemUser> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "userId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findUserDetailPage(page, user);
    }


    private void setUserRoles(SystemUser user, String[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Long.valueOf(roleId));
            userRoleService.save(ur);
        });
    }

    private void setUserDataPermissions(SystemUser user, String[] deptIds) {
        List<UserDataPermission> userDataPermissions = new ArrayList<>();
        Arrays.stream(deptIds).forEach(deptId -> {
            UserDataPermission permission = new UserDataPermission();
            permission.setDeptId(Long.valueOf(deptId));
            permission.setUserId(user.getUserId());
            userDataPermissions.add(permission);
        });
        userDataPermissionMapper.saveBatch(userDataPermissions);
    }
}
