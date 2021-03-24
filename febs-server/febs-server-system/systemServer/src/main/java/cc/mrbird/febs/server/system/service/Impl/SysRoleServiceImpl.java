package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.constant.FebsConstant;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.common.utils.StringUtils;
import cc.mrbird.febs.server.system.service.ISysRoleService;
import cc.mrbird.febs.server.system.mapper.SysRoleMapper;
import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public Page<SysRole> findAllRolesByPage(QueryRequest queryRequest, String roleName) {

        Page<SysRole> page = new Page<>();
        SortUtil.handlePageSort(queryRequest, page, "roleId", FebsConstant.ORDER_ASC, true);
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(roleName)){
            queryWrapper.lambda().like(SysRole::getRoleName,roleName);
        }
        this.baseMapper.selectPage(page,queryWrapper);
        return page;
    }
}