package cc.mrbird.febs.server.system.Service;

import cc.mrbird.febs.common.entity.system.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
public interface ISysDeptService extends IService<SysDept> {


    List<SysDept> getDeptByParentId(Long parentId);
}

