package cc.mrbird.febs.server.system.Service;

import cc.mrbird.febs.server.system.vo.SysDictData;
import cc.mrbird.febs.server.system.vo.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 字典类型表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
public interface ISysDictTypeService extends IService<SysDictType> {

    List<SysDictData> getDictDataByDictType(String dictType);
}

