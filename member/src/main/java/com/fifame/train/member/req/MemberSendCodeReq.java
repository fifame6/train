package com.fifame.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @ClassName MemberSendCodeReq
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 10:16
 * @Version 1.0
 */
@Data
public class MemberSendCodeReq {
    @Pattern(regexp = "^1\\d{10}$",message = "手机号码格式错误")
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}

