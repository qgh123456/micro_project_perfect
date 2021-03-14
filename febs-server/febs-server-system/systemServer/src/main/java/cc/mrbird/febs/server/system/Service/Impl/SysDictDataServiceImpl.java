package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysDictDataService;
import cc.mrbird.febs.server.system.mapper.SysDictDataMapper;
import cc.mrbird.febs.server.system.vo.SysDictData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("sysDictDataService")
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {



}