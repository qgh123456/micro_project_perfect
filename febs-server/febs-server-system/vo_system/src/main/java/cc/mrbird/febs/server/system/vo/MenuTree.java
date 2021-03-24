package cc.mrbird.febs.server.system.vo;

import cc.mrbird.febs.common.entity.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/3/24 17:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends Tree<SysMenu> {

    private Integer orderNum;
}
