package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.common.entity.system.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
}
