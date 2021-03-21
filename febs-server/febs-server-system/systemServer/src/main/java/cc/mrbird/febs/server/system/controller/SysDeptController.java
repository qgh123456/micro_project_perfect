package cc.mrbird.febs.server.system.controller;

import java.util.List;
import java.util.Map;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.server.system.service.ISysDeptService;
import cc.mrbird.febs.server.system.vo.SysDept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门表
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-20 18:16:51
 */
@Api(tags = "部门表 管理")
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Autowired
    private ISysDeptService sysDeptService;

    @GetMapping("getDeptByParentId")
    public Result<SysDept> getDeptByParentId(@RequestParam(value = "parentId") Long parentId){

        List<SysDept> sysDeptList = this.sysDeptService.getDeptByParentId(parentId);
        return Result.ok().data(sysDeptList);
    }

    @GetMapping("/getDepts")
    public Result getDepts(@ApiParam(name = "SysDept", value = "查询对象", required = false) SysDept sysDept){

        List<SysDept> sysDepts = sysDeptService.getDepts(sysDept);
        return Result.ok().data(sysDepts);
    }

    @GetMapping("/getDeptTree")
    public Result getDeptTree(@ApiParam(name = "QueryRequest", value = "查询对象", required = false)
                                    QueryRequest requestQuery,
                              @ApiParam(name = "SysDept", value = "查询对象", required = false)
                                    SysDept sysDept){

        Map<String, Object> deptTree = sysDeptService.getDeptTree(requestQuery, sysDept);
        return Result.ok().data(deptTree);
    }

}
