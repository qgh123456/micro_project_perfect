package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.server.system.vo.SysRoleDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和部门关联表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {
	
}
