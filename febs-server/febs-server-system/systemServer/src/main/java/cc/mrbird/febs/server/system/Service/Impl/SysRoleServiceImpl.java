package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysRoleService;
import cc.mrbird.febs.server.system.mapper.SysRoleMapper;
import cc.mrbird.febs.server.system.vo.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;



@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {


}