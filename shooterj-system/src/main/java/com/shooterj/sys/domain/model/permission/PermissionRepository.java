package com.shooterj.sys.domain.model.permission;

import java.util.List;
import java.util.Map;

/**
 * 权限-Repository接口
 *
 * @author shooterj
 **/
public interface PermissionRepository {

    /**
     * 查找菜单
     *
     * @param params
     * @return
     */
    List<Permission> queryList(Map<String, Object> params);

}
