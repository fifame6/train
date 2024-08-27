package com.fifame.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @ClassName MemberLoginReq
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 12:06
 * @Version 1.0
 */
@Data
public class MemberLoginReq {
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号码格式错误")
    private String mobile;

    @NotBlank(message = "短信验证码不能为空")
    private String code;
}

