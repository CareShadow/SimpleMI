package com.shadow.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadow.context.UserContext;
import com.shadow.enums.ResultCode;
import com.shadow.utils.JwtUtil;
import com.shadow.vo.ResultVO;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/25 22:52
 * @Version 1.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/admin/login".equals(request.getRequestURI())) {
            return true;
        }

        Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
        if (claims != null) {
            UserContext.add(claims.getSubject());
            return true;
        }

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultVO resultVO = new ResultVO<String>(ResultCode.UNAUTHORIZED,"请先登录");
        String responseStr = mapper.writeValueAsString(resultVO);
        PrintWriter out = response.getWriter();
        out.write(responseStr);
        out.flush();
        out.close();
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 防止内存溢出
        UserContext.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
