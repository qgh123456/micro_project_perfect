package cc.mrbird.febs.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qiguohui
 * @since 2020/03/11
 * @desc  mp 关于时间的自动配置  对应的字段要加  @TableField(fill = FieldFill.INSERT_UPDATE)
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime", new Date(), metaObject);//自动添加数据（插入）
		this.setFieldValByName("modifyTime", new Date(), metaObject);//自动添加数据（插入）
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("modifyTime", new Date(), metaObject);//自动添加数据（修改）
	}
}