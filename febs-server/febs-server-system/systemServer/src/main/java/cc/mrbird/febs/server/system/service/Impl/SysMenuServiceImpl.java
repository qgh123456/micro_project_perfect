package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.server.system.service.ISysMenuService;
import cc.mrbird.febs.server.system.mapper.SysMenuMapper;
import cc.mrbird.febs.server.system.vo.SysMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {



}