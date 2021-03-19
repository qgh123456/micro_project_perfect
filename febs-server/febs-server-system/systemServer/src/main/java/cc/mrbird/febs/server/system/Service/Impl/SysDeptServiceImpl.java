package cc.mrbird.febs.server.system.Service.Impl;

import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.server.system.Service.ISysDeptService;
import cc.mrbird.febs.server.system.mapper.SysDeptMapper;
import cc.mrbird.febs.server.system.vo.SysDept;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<SysDept> getDepts(SysDept sysDept) {

        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if(sysDept.getDeptId() != null){
            queryWrapper.eq("dept_id",sysDept.getDeptId());
        }
        if (null != sysDept.getParentId()){
            queryWrapper.eq("parent_id",sysDept.getParentId());
        }
        if (StringUtils.isNotEmpty(sysDept.getAncestors())){
            queryWrapper.eq("ancestors",sysDept.getAncestors());
        }
        if(StringUtils.isNotEmpty(sysDept.getDeptName())){
            queryWrapper.like("dept_name",sysDept.getDeptName());
        }
        if(StringUtils.isNotEmpty(sysDept.getLev())){
            queryWrapper.like("lev",sysDept.getLev());
        }
        if(StringUtils.isNotEmpty(sysDept.getLeader())){
            queryWrapper.eq("leader",sysDept.getLeader());
        }
        if(StringUtils.isNotEmpty(sysDept.getStatus())){
            queryWrapper.eq("status",sysDept.getStatus());
        }
        return sysDeptMapper.selectList(queryWrapper);
    }

    @Override
    public void getDeptTree(Page<SysDept> pageParam, SysDept sysDept) {

        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysDept.getDeptName())){
            queryWrapper.lambda().like(SysDept::getDeptName,sysDept.getDeptName());
        }
        if (StringUtils.isNotBlank(sysDept.getCreateTimeFrom()) && StringUtils.isNotBlank(sysDept.getCreateTimeTo())){
            queryWrapper.lambda()
                    .ge(SysDept::getCreateTime,sysDept.getCreateTimeFrom())
                    .le(SysDept::getCreateTime,sysDept.getCreateTimeTo());
        }
        SortUtil
    }
}