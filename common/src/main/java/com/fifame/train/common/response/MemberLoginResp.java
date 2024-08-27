package com.fifame.train.common.response;

import lombok.Data;

/**
 * @ClassName MemberLoginResp
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 12:34
 * @Version 1.0
 */
@Data
public class MemberLoginResp {
    private Long id;
    private String mobile;
    private String token;
}

