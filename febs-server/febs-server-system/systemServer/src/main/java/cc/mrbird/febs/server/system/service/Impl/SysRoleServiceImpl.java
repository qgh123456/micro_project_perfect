package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.constant.FebsConstant;
import cc.mrbird.febs.common.entity.constant.StringConstant;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.common.utils.StringUtils;
import cc.mrbird.febs.server.system.mapper.SysMenuMapper;
import cc.mrbird.febs.server.system.mapper.SysRoleMenuMapper;
import cc.mrbird.febs.server.system.service.ISysRoleService;
import cc.mrbird.febs.server.system.mapper.SysRoleMapper;
import cc.mrbird.febs.server.system.vo.SysRole;
import cc.mrbird.febs.server.system.vo.SysRoleMenu;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

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

    @Override
    public boolean checkRoleName(String roleName) {

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(roleName)){
            queryWrapper.lambda().eq(SysRole::getRoleName,roleName);
        }
        Integer count = this.sysRoleMapper.selectCount(queryWrapper);
        return count == 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRole sysRole) {

        // 保存角色
        this.sysRoleMapper.insert(sysRole);
        // 保存权限
        if(StringUtils.isNotBlank(sysRole.getMenuIds())){
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(sysRole.getMenuIds(), StringConstant.COMMA);
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            for (String menuId : menuIds){
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(Long.valueOf(menuId));
                this.sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRole sysRole) {

        // 修改角色
        this.sysRoleMapper.updateById(sysRole);
        /**
         *  删除该角色下的所有权限
         */
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleMenu::getRoleId,sysRole.getRoleId());
        this.sysRoleMenuMapper.delete(queryWrapper);
        // 保存权限
        if(StringUtils.isNotBlank(sysRole.getMenuIds())){
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(sysRole.getMenuIds(), StringConstant.COMMA);
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            for (String menuId : menuIds){
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(Long.valueOf(menuId));
                this.sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public void delRoles(String roleIds) {

        String[] roleIdArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(roleIds, StringConstant.COMMA);
        List<String> roleIdList = Arrays.asList(roleIdArray);
        // 删除角色表
        this.sysRoleMapper.deleteBatchIds(roleIdList);
        // 删除角色关联的权限表
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        roleIdList.forEach(item ->{
            queryWrapper.lambda().eq(SysRoleMenu::getRoleId,item);
            this.sysRoleMenuMapper.delete(queryWrapper);
        });
    }
}