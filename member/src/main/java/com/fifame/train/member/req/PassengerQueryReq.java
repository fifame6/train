package com.fifame.train.member.req;

import com.fifame.train.common.req.PageReq;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PassengerQueryReq extends PageReq {



    private Long memberId;



}