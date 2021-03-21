package cc.mrbird.febs.server.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Data
@TableName("t_dept")
public class SysDept implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门id
	 */
	@TableId
	private Long deptId;
	/**
	 * 父部门id
	 */
	private Long parentId;
	/**
	 * 祖级列表
	 */
//	private String ancestors;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 显示顺序
	 */
	private Integer orderNum;

	/**
	 * 创建者
	 */
//	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者
	 */
//	private String updateBy;

//	private String lev;
	/**
	 * 更新时间
	 */
	@TableField(value = "MODIFY_TIME")
	private Date modifyTime;
	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	@TableLogic
	private String delFlag;

	@TableField(exist = false)
	private String createTimeFrom;

	@TableField(exist = false)
	private String createTimeTo;

}
