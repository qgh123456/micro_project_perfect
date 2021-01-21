package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.common.entity.system.SysDept;
import cc.mrbird.febs.server.system.Service.ISysDeptService;
import cc.mrbird.febs.server.system.mapper.SysDeptMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Override
    public List<SysDept> getDeptByParentId(Long parentId) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        return this.sysDeptMapper.selectList(queryWrapper);
    }
}