package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.common.entity.system.SysDictData;
import cc.mrbird.febs.common.entity.system.SysDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型表
 * 
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictData> getDictDataByDictType(String dictType);

}
