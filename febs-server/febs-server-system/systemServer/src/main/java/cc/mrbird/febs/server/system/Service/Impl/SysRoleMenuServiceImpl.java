package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysRoleMenuService;
import cc.mrbird.febs.server.system.mapper.SysRoleMenuMapper;
import cc.mrbird.febs.server.system.vo.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {



}