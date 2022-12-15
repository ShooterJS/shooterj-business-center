package com.shooterj.sys.domain.model.permission;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

/**
 * 菜单地址
 **/
public class MenuUrl implements ValueObject<MenuUrl> {

    private String url;

    public MenuUrl(final String url) {
        Preconditions.checkNotNull(url,"菜单地址不能为空");
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean sameValueAs(MenuUrl other) {
        return other != null && this.url.equals(other.url);
    }

    @Override
    public String toString() {
        return url;
    }
}
