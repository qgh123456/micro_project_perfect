package cc.mrbird.febs.server.system.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Data
@TableName("sys_dict_type")
public class SysDictType implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 字典主键
	 */
	@TableId
	private Long dictId;
	/**
	 * 字典名称
	 */
	private String dictName;
	/**
	 * 字典类型
	 */
	private String dictType;
	/**
	 * 状态（0正常 1停用）
	 */
	private String status;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 0 未删除 1 已删除
	 */
	private String delFlag;

}
