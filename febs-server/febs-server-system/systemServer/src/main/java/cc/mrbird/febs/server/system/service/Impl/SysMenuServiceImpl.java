package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.Tree;
import cc.mrbird.febs.common.entity.system.Menu;
import cc.mrbird.febs.common.utils.StringUtils;
import cc.mrbird.febs.common.utils.TreeUtil;
import cc.mrbird.febs.server.system.service.ISysMenuService;
import cc.mrbird.febs.server.system.mapper.SysMenuMapper;
import cc.mrbird.febs.server.system.vo.DeptTree;
import cc.mrbird.febs.server.system.vo.MenuTree;
import cc.mrbird.febs.server.system.vo.SysDept;
import cc.mrbird.febs.server.system.vo.SysMenu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public Map<String, Object> getMenuTree(String menuName) {

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menuName)) {
            queryWrapper.lambda().like(SysMenu::getMenuName,menuName);
        }
        queryWrapper.lambda().orderByAsc(SysMenu::getOrderNum);
        List<SysMenu> sysMenus = this.baseMapper.selectList(queryWrapper);
        List<MenuTree> trees = new ArrayList<>();
        buildTrees(trees, sysMenus);
        List<? extends Tree<?>> menuTree = TreeUtil.build(trees);
        Map<String, Object> result = new HashMap<>();
        result.put("rows",menuTree);
        return result;
    }

    private void buildTrees(List<MenuTree> trees, List<SysMenu> sysMenus) {
        sysMenus.forEach(item -> {
            MenuTree tree = new MenuTree();
            tree.setId(item.getMenuId().toString());
            tree.setParentId(item.getParentId().toString());
            tree.setLabel(item.getMenuName());
            tree.setOrderNum(item.getOrderNum());
            trees.add(tree);
        });
    }
}