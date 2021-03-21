package cc.mrbird.febs.server.system.service.Impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Tree;
import cc.mrbird.febs.common.entity.constant.FebsConstant;
import cc.mrbird.febs.common.entity.constant.PageConstant;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.common.utils.TreeUtil;
import cc.mrbird.febs.server.system.service.ISysDeptService;
import cc.mrbird.febs.server.system.mapper.SysDeptMapper;
import cc.mrbird.febs.server.system.vo.DeptTree;
import cc.mrbird.febs.server.system.vo.SysDept;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        if(StringUtils.isNotEmpty(sysDept.getDeptName())){
            queryWrapper.like("dept_name",sysDept.getDeptName());
        }

        return sysDeptMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getDeptTree(QueryRequest requestQuery, SysDept sysDept) {

        Map<String, Object> result = new HashMap<>();
        try {
            List<SysDept> depts = this.findDepts(sysDept, requestQuery);
            List<DeptTree> trees = new ArrayList<>();
            buildTrees(trees, depts);
            List<? extends Tree<?>> deptTree = TreeUtil.build(trees);
            result.put(PageConstant.ROWS, deptTree);
            result.put(PageConstant.TOTAL, depts.size());
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            result.put(PageConstant.ROWS, null);
            result.put(PageConstant.TOTAL, 0);
        }
        return result;
    }

    @Override
    public List<SysDept> findDepts(SysDept sysDept, QueryRequest request) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(sysDept.getDeptName())) {
            queryWrapper.lambda().like(SysDept::getDeptName, sysDept.getDeptName());
        }
        if (StringUtils.isNotBlank(sysDept.getCreateTimeFrom()) && StringUtils.isNotBlank(sysDept.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(SysDept::getCreateTime, sysDept.getCreateTimeFrom())
                    .le(SysDept::getCreateTime, sysDept.getCreateTimeTo());
        }
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", FebsConstant.ORDER_ASC,
                true);
        return this.baseMapper.selectList(queryWrapper);
    }

    private void buildTrees(List<DeptTree> trees, List<SysDept> sysDepts) {
        sysDepts.forEach(item -> {
            DeptTree tree = new DeptTree();
            tree.setId(item.getDeptId().toString());
            tree.setParentId(item.getParentId().toString());
            tree.setLabel(item.getDeptName());
            tree.setOrderNum(item.getOrderNum());
            trees.add(tree);
        });
    }
}