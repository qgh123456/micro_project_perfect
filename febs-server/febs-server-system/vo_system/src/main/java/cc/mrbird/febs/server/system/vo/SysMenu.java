package cc.mrbird.febs.server.system.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权限表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Data
@TableName("t_menu")
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单/按钮ID
	 */
	@TableId(value = "menu_id", type = IdType.AUTO)
	private Long menuId;

	/**
	 * 上级菜单ID
	 */
	@TableField("parent_id")
	private Long parentId;

	/**
	 * 菜单/按钮名称
	 */
	@TableField("menu_name")
	private String menuName;

	/**
	 * 菜单URL
	 */
	@TableField("path")
	private String path;

	/**
	 * 对应 Vue组件
	 */
	@TableField("component")
	private String component;

	/**
	 * 权限标识
	 */
	@TableField("perms")
	private String perms;

	/**
	 * 图标
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 排序
	 */
	@TableField("order_num")
	private Integer orderNum;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField(value = "MODIFY_TIME",fill = FieldFill.INSERT_UPDATE)
	private Date modifyTime;


	/**
	 * 菜单类型（M目录 C菜单 F按钮）
	 */
	@TableField("type")
	private String type;

	/**
	 * 0 未删除 1 已删除
	 */
	@TableLogic
	@TableField("del_flag")
	private String delFlag;


}
