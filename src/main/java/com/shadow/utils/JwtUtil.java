package com.shadow.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/25 19:52
 * @Version 1.0
 **/
@Slf4j
public final class JwtUtil {
    private final static String secretKey = "careshadow";
    private final static Duration expiration = Duration.ofHours(2);

    /**
     * 生成JWT
     *
     * @param username
     * @return
     */
    public static String generate(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Claims parse(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
           log.debug("Token解析失败");
        }
        return claims;
    }

}
