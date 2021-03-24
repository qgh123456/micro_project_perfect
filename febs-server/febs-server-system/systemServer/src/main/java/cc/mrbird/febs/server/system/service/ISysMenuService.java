package cc.mrbird.febs.server.system.service;

import cc.mrbird.febs.server.system.vo.MenuTree;
import cc.mrbird.febs.server.system.vo.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 菜单权限表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
public interface ISysMenuService extends IService<SysMenu> {

    Map<String, Object> getMenuTree(String menuName);
}

