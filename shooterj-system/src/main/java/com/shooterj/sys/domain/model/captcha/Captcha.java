package com.shooterj.sys.domain.model.captcha;


import com.shooterj.common.domain.Entity;
import com.shooterj.common.util.MyDateUtil;

import java.util.Date;

/**
 * 验证码
 **/
public class Captcha implements Entity<Captcha> {

    private Uuid uuid;
    /**
     * 验证码
     */
    private CaptchaCode captchaCode;
    /**
     * 过期时间
     */
    private Date expireTime;

    public Captcha(Uuid uuid, CaptchaCode captchaCode, Date expireTime) {
        this.uuid = uuid;
        this.captchaCode = captchaCode;
        this.expireTime = expireTime;
    }

    public static Captcha createCaptcha(Uuid uuid, CaptchaCode captchaCode) {
        return new Captcha(uuid, captchaCode, MyDateUtil.addDateMinutes(new Date(), 5));
    }

    public boolean validate(CaptchaCode captchaCode) {
        return this.captchaCode.sameValueAs(captchaCode) && this.expireTime.getTime() >= System.currentTimeMillis();
    }

    @Override
    public boolean sameIdentityAs(Captcha other) {
        return false;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public CaptchaCode getCaptchaCode() {
        return captchaCode;
    }

    public Date getExpireTime() {
        return expireTime;
    }
}
