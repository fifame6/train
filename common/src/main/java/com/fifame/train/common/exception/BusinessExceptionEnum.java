package com.fifame.train.common.exception;

/**
 * @ClassName BusinessException
 * @Description 自定义枚举值,来自定义异常类型,将来随着业务的增加而增加更多的枚举值
 * @Author fifame
 * @DATE 2023-09-27 8:29
 * @Version 1.0
 */

public enum BusinessExceptionEnum {

    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    MEMBER_MOBILE_CODE_ERROR("验证码错误!"),
    MEMBER_MOBILE_EXIST("手机号已存在");// 分号分隔常量与其他部分
    private String desc;
    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "desc='" + desc + '\'' +
                '}';
    }
}

