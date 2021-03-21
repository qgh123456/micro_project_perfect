package cc.mrbird.febs.server.system.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.server.system.vo.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * 部门表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
public interface ISysDeptService extends IService<SysDept> {


    List<SysDept> getDeptByParentId(Long parentId);

    List<SysDept> getDepts(SysDept sysDept);

    Map<String, Object> getDeptTree(QueryRequest requestQuery, SysDept sysDept);

    List<SysDept> findDepts(SysDept sysDept, QueryRequest request);
}

