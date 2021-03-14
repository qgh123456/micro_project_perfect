package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysDictTypeService;
import cc.mrbird.febs.server.system.mapper.SysDictTypeMapper;
import cc.mrbird.febs.server.system.vo.SysDictData;
import cc.mrbird.febs.server.system.vo.SysDictType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    @Override
    public List<SysDictData> getDictDataByDictType(String dictType) {
        return this.sysDictTypeMapper.getDictDataByDictType(dictType);
    }
}