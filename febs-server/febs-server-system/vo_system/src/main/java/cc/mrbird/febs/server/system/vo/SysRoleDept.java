package cc.mrbird.febs.server.system.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和部门关联表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Data
@TableName("sys_role_dept")
public class SysRoleDept implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId
	private Long roleId;
	/**
	 * 部门ID
	 */
	private Long deptId;

}
