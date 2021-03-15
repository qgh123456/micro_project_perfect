package cc.mrbird.febs.server.system.controller;

import java.util.Arrays;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.system.SysOperLog;
import cc.mrbird.febs.server.system.Service.ISysOperLogService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志记录
 *
 * @author lixianfeng
 * @email lxf@atguigu.com
 * @date 2021-01-17 17:48:48
 */
@Api(tags = "操作日志记录 管理")
@RestController
@RequestMapping("/sysoperlog")
public class SysOperLogController {
    @Autowired
    private ISysOperLogService sysOperLogService;

    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{operId}")
    public Result<SysOperLog> info(@PathVariable("operId") Long operId){
		SysOperLog sysOperLog = sysOperLogService.getById(operId);

        return Result.ok().data(sysOperLog);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody SysOperLog sysOperLog){
		sysOperLogService.save(sysOperLog);

        return Result.ok();
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/saveForMq")
    public Result saveForMq(@RequestParam("messageData") String messageData){
        // 转化为对象
        SysOperLog sysOperLog = JSONObject.parseObject(messageData, SysOperLog.class);
        sysOperLogService.save(sysOperLog);
        return Result.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public Result<Object> update(@RequestBody SysOperLog sysOperLog){
		sysOperLogService.updateById(sysOperLog);

        return Result.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody Long[] operIds){
		sysOperLogService.removeByIds(Arrays.asList(operIds));

        return Result.ok();
    }

}
