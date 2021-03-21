package cc.mrbird.febs.server.system.vo;

import cc.mrbird.febs.common.entity.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/3/18 20:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends Tree<SysDept> {

    private Integer orderNum;
}
