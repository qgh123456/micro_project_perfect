package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.Result;
import cc.mrbird.febs.common.entity.system.SystemUser;
import cc.mrbird.febs.server.system.service.IUserDataPermissionService;
import cc.mrbird.febs.server.system.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private IUserDataPermissionService userDataPermissionService;

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
    public Result deleteUserById(@ApiParam(name = "userId", value = "用户id")
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

    @GetMapping("/check/{username}")
    public Result check(@ApiParam(name = "username", value = "用户名称")
                            @PathVariable(value = "username") String username){

        // 构建查询参数
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        int count = userService.count(queryWrapper);
        return Result.ok().data(count);

    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('user:add')")
    public Result addUser(SystemUser systemUser){
        userService.createUser(systemUser);
        return Result.ok();
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasAuthority('user:update')")
    public Result updateUser(SystemUser systemUser){
        userService.updateUser(systemUser);
        return Result.ok();
    }

    @GetMapping("/getDetail/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public Result getDetail(@PathVariable(value = "userId") Long userId){

        String dataPermissions = this.userDataPermissionService.findByUserId(userId.toString());
        return Result.ok().data(dataPermissions);
    }

    @PutMapping("/password/reset")
    @PreAuthorize("hasAuthority('user:update')")
    public Result passwordReset(String userIds){
        if (org.apache.commons.lang3.StringUtils.isNotBlank(userIds)){
            String[] userIdList = userIds.split(",");
            this.userService.passwordReset(userIdList);
        }
        else{
            return Result.error("请选择数据");
        }
        return Result.ok();
    }

    @PostMapping("excel")
    @PreAuthorize("hasAuthority('user:export')")
    public void export(QueryRequest queryRequest, SystemUser user, HttpServletResponse response) {

        List<SystemUser> users = this.userService.findUserDetailList(user, queryRequest).getRecords();
        ExcelKit.$Export(SystemUser.class, response).downXlsx(users, false);
    }
}
