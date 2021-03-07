package com.player.common.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2020/4/19 15:31
 */
@Component
public class JwtToken {

    private  String secret = "wuwenqiang";

    /**
     * 生成jwt token
     *
     * @param value
     * @param expirationTime
     * @return
     */
    public String createToken(Object value, long expirationTime) {
        // 生成SecretKey 对象
        SecretKey secretKey = this.createSecretKey();
        String jsonValue = JSONObject.toJSONString(value);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder().setIssuedAt(now).setSubject(jsonValue).signWith(signatureAlgorithm, secretKey);
        if (expirationTime > 0L) {
            long expMillis = nowMillis + expirationTime;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);  // 设置token过期时间
        }
        return jwtBuilder.compact();
    }

    private SecretKey createSecretKey() {
        byte[] bytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(bytes, 0, bytes.length, "AES");
    }

    /**
     * 解析token
     *
     * @param token
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T parserToken(String token, Class<T> clazz) {
        SecretKey secretKey = this.createSecretKey();
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String subject = claims.getSubject();
            return (T) JSONObject.parseObject(subject, clazz);
        } catch (SignatureException | MalformedJwtException e) {
            return null;
        } catch (ExpiredJwtException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
