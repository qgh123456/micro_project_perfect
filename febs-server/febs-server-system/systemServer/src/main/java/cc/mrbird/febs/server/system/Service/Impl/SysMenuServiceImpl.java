package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.server.system.Service.ISysMenuService;
import cc.mrbird.febs.server.system.mapper.SysMenuMapper;
import cc.mrbird.febs.server.system.vo.SysMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {



}