package cc.mrbird.febs.server.system.mapper;

import cc.mrbird.febs.server.system.vo.UserDataPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MrBird
 */
@Repository
public interface UserDataPermissionMapper extends BaseMapper<UserDataPermission> {


    void saveBatch(List<UserDataPermission> userDataPermissions);
}
