package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysRoleService;
import cc.mrbird.febs.server.system.mapper.SysRoleMapper;
import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findAllRoles() {

        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysRole::getRoleId);
        return sysRoleMapper.selectList(queryWrapper);
    }
}