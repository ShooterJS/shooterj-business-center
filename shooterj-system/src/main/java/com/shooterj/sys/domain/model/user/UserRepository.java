package com.shooterj.sys.domain.model.user;

import java.util.List;

public interface UserRepository {
    /**
     * 保存
     */
    UserId store(User user);

    /**
     * 根据手机号获取账号
     *
     * @param mobile
     * @return
     */
    List<User> find(Mobile mobile);

}
