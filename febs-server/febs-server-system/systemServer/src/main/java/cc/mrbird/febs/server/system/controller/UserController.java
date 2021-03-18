package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.server.system.Service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/7 18:13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/pageQuery")
    public Result getList(@ApiParam(name = "pageNum", value = "当前页码", defaultValue = "1")
                                @RequestParam(value = "pageNum") Long pageNum,
                          @ApiParam(name = "pageSize", value = "每页记录数", defaultValue = "10")
                                @RequestParam(value = "pageSize") Long pageSize,
                          @ApiParam(name = "systemUser", value = "查询对象", required = false)
                                      SystemUser systemUser
            ){
        //封装分页
        Page<SystemUser> pageParam = new Page<>(pageNum,pageSize);
        userService.queryPage(pageParam,systemUser);
        return Result.ok().data(pageParam);

    }

    @DeleteMapping("/deleteUserById/{userId}")
    public Result deleteUserById(@ApiParam(name = "userId", value = "当前页码")
                                     @PathVariable(value = "userId") String userId){

        String[] ids = StringUtils.split(userId, ",");
        if(ids.length > 0){
            List<String> idList = Arrays.asList(ids);
            boolean isRemoved = userService.removeByIds(idList);
            if(isRemoved){
                return Result.ok().message("删除成功~");
            }
            else {
                return Result.error("删除失败~");
            }
        }
        else {
            return Result.error("请选择删除的数据~");
        }

    }

}
