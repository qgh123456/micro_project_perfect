package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.system.SysOperLog;
import cc.mrbird.febs.server.system.service.ISysOperLogService;
import cc.mrbird.febs.server.system.mapper.SysOperLogMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("sysOperLogService")
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {


}