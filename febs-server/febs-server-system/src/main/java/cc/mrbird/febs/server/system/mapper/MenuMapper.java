package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.common.entity.system.Menu;
import cc.mrbird.febs.common.entity.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户名查询权限信息
     *
     * @param username 用户名称
     * @return 权限信息
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 通过用户名查询菜单信息
     *
     * @param username 用户名
     * @return 菜单信息
     */
    List<Menu> findUserMenus(String username);
}
