package cc.mrbird.febs.server.system.controller;

import java.util.Arrays;
import java.util.List;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.server.system.Service.ISysDeptService;
import cc.mrbird.febs.server.system.vo.SysDept;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Result getDeptTree(@ApiParam(name = "SysDept", value = "查询对象", required = false) SysDept sysDept){

        //封装分页
        Page<SysDept> pageParam = new Page<>();
        sysDeptService.getDeptTree(pageParam,sysDept);
        return Result.ok().data(pageParam);
    }

}
