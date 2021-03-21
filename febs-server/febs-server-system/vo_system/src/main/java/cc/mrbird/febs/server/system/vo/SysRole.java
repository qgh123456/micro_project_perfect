package cc.mrbird.febs.server.system.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Data
@TableName("t_role")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "ROLE_ID", type = IdType.AUTO)
	private Long roleId;

	@TableField(value = "ROLE_NAME")
	private String roleName;

	@TableField(value = "REMARK")
	@Size(max = 50, message = "{noMoreThan}")
	private String remark;

	@TableField(value = "CREATE_TIME")
	private Date createTime;

	@TableField(value = "MODIFY_TIME")
	private Date modifyTime;

	private transient String menuIds;
	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	@TableLogic
	private String delFlag;

}
