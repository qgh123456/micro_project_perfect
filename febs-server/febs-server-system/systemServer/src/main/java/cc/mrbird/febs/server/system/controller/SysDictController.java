package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.server.system.Service.ISysDictTypeService;
import cc.mrbird.febs.server.system.Service.Impl.SysDeptServiceImpl;
import cc.mrbird.febs.server.system.vo.SysDictData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/20 23:33
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/sysDict")
public class SysDictController {

    @Autowired
    private ISysDictTypeService sysDictTypeService;

    @GetMapping("/getDictDataByDictType")
    public Result<SysDictData> getDictDataByDictType(@RequestParam(value = "dictType") String dictType){

        List<SysDictData> sysDictDataList = sysDictTypeService.getDictDataByDictType(dictType);
        return Result.ok().data(sysDictDataList);

    }
}
