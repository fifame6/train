package com.fifame.train.member.enums;

/**
 * @ClassName PassengerTypeEnum
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 18:05
 * @Version 1.0
 */
public enum PassengerTypeEnum {

    ADULT("1","成人"),
    CHILD("2","儿童"),
    STUDENT("3","学生")
    ;


    private String code;
    private String desc;

    private PassengerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "PassengerTypeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

