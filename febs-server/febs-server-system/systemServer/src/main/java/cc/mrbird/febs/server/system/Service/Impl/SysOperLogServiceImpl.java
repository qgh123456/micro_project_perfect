package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.common.entity.system.SysOperLog;
import cc.mrbird.febs.server.system.Service.ISysOperLogService;
import cc.mrbird.febs.server.system.mapper.SysOperLogMapper;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("sysOperLogService")
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {


}