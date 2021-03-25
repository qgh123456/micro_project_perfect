package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.server.system.vo.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色和菜单关联表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:52
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
}
