package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import feign.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/7 11:50
 */
@Repository
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 查找用户详细信息
     *
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<SystemUser> findUserDetailPage(Page page, @Param("user") SystemUser user);

    List<SystemUser> queryPage(Map<String, Object> queryMap);

    Long countByQueryMap(Map<String, Object> queryMap);

    void batchUptPasswordReset(String[] usernameList);
}
