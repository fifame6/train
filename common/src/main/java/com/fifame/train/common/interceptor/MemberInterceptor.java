package com.fifame.train.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fifame.train.common.context.LoginMemberContext;
import com.fifame.train.common.response.MemberLoginResp;
import com.fifame.train.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName MemberInterceptor
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-27 23:10
 * @Version 1.0
 */
@Component
public class MemberInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("MemberInterceptor开始");
        String token = request.getHeader("token");
        if (StrUtil.isNotEmpty(token)) {
            LOG.info("获取会员登录token：{}", token);
            //将请求头中的token解密以获取member的值
            JSONObject jsonObject = JwtUtil.getJSONObject(token);
            LOG.info("当前登录会员：{}", jsonObject);
            MemberLoginResp memberLoginResp = JSONUtil.toBean(jsonObject, MemberLoginResp.class);
            LoginMemberContext.setMember(memberLoginResp);
        }
        LOG.info("MemberInterceptor结束");
        return true;
    }
}

