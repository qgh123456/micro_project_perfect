package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色信息表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
}
