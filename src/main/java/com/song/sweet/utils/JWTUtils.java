package com.song.sweet.utils;

import com.song.sweet.model.User;
import io.jsonwebtoken.*;

import java.util.*;

/**
 * @author Liwen
 * @Description // JWT工具类：用于生成Token，和Token验证
 * @Version 1.0.0
 * @create 2020-09-02 17:26
 */
public class JWTUtils {

    /**
     * 请求头中token的头
     */
    //@Value("${jwt.header}")
    public static final String TOKEN_HEADER = "token";

    /**
     * token的前缀
     */
    // @Value("${jwt.tokenHead}")
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * JWT的发行人
     */
    // @Value("${jwt.iss}")
    private static final String ISS = "liwen";

    /**
     * 密钥key
     */
    // @Value("${jwt.secret}")
    private static final String SECRET = "zr1CAapZa43EhtHzHY03pQ==";

    /**
     * 自定义信息中用户ID
     */
    private static final String UID_CLAIMS = "userId";

    /**
     * 自定义信息中用户名
     */
    private static final String UNAME_CLAIMS = "username";

    /**
     * 自定义信息中用户角色
     */
    private static final String ROLE_CLAIMS = "role";

    /**
     * 过期时间是3600秒，既是60min
     */
    public static final long EXPIRATION = 3600L * 1000;

    /**
     * 选择了记住我之后的过期时间为1天
     */
    public static final long EXPIRATION_REMEMBER = 24 * 3600L * 1000;

    /**
     * @return String
     * @Title createToken
     * @Description // 根据用户信息创建TOKEN
     * @Author liwen
     * @Date 2020/9/2 17:29
     * @Param user
     */
    public static String createToken(User user, boolean isRememberMe) {
        // 如果选择记住我，则token的过期时间为
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, "all"); // 角色名字
        map.put(UID_CLAIMS, user.getId()); // 用户ID
        map.put(UNAME_CLAIMS, user.getUsername()); // 用户名
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET) // 加密算法
                .setClaims(map) // 自定义信息
                .setIssuer(ISS) // jwt发行人
                .setIssuedAt(new Date()) // jwt发行时间
                .setSubject(user.getUsername()) // jwt面向的用户
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // key过期时间
                .compact();
    }

    /**
     * @return java.lang.String
     * @Title refreshToken
     * @Description // 刷新token
     * @Author liwen
     * @Date 2020/9/2 17:52
     * @Param [token, always]
     */
    public static String refreshToken(String token, boolean always) {
        boolean canRefresh = canRefresh(token);
        String newToken = token;
        if (canRefresh == true || always == true) {
            Claims claims = getTokenClaims(token);
            HashMap<String, Object> map = new HashMap<>();
            map.put(UID_CLAIMS, claims.get(UID_CLAIMS));
            map.put(UNAME_CLAIMS, claims.get(UNAME_CLAIMS));
            map.put(ROLE_CLAIMS, claims.get(ROLE_CLAIMS));
            newToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET) // 加密算法
                    .setClaims(map) // 自定义信息
                    .setIssuer(ISS) // jwt发行人
                    .setIssuedAt(new Date()) // jwt发行时间
                    .setSubject((String) claims.get(UNAME_CLAIMS)) // jwt面向的用户
                    .setExpiration(new Date(System.currentTimeMillis()
                            + (claims.getExpiration().getTime() - claims.getIssuedAt().getTime()))) // key过期时间
                    .compact();
        }
        return newToken;
    }

    /**
     * @return boolean
     * @Title validateToken
     * @Description // 验证token true-有效 false-过期
     * @Author liwen
     * @Date 2020/9/2 17:53
     * @Param [token]
     */
    public static Boolean validateToken(String token) {
        try {
            Claims claims = getTokenClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            return Boolean.FALSE;
        } catch (SignatureException e) {
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * @return com.song.sweet.model.User
     * @Title getUserInfo
     * @Description // 获取用户信息
     * @Author liwen
     * @Date 2020/9/2 17:45
     * @Param [token]
     */
    public static User getUserInfo(String token) {
        Claims claims = getTokenClaims(token);
        User user = null;
        if (claims != null && claims.size() > 0) {
            user = new User();
            if (claims.get(UID_CLAIMS) != null) {
                user.setId((Long) claims.get(UID_CLAIMS));
            }
            if (claims.get(UNAME_CLAIMS) != null) {
                user.setUsername((String) (claims.get(UNAME_CLAIMS)));
            }
        }
        return user;
    }

    /**
     * @return long
     * @Title getExpireTime
     * @Description //  获取Token有效期ms
     * @Author liwen
     * @Date 2020/9/2 17:46
     * @Param [token]
     */
    public static long getExpireTime(String token) {
        Claims claims = getTokenClaims(token);
        return claims.getExpiration().getTime() - claims.getIssuedAt().getTime();
    }

    /**
     * @return java.lang.Integer
     * @Title getUserID
     * @Description // 从token中获取用户ID
     * @Author liwen
     * @Date 2020/9/2 17:48
     * @Param [token]
     */
    public static Integer getUserID(String token) {
        return (Integer) getTokenClaims(token).get(UID_CLAIMS);
    }

    /**
     * @return java.lang.String
     * @Title getUsername
     * @Description // 从token获取用户名称
     * @Author liwen
     * @Date 2020/9/2 17:46
     * @Param [token]
     */
    public static String getUsername(String token) {
        return getTokenClaims(token).getSubject();
    }

    public static boolean isExpiration(String token) {
        token = token.substring(JWTUtils.TOKEN_PREFIX.length());
        Claims claims = null;
        try {
            claims = getTokenClaims(token);
        } catch (ExpiredJwtException jwtException) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims.getExpiration().after(new Date());
    }

    private static Claims getTokenClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public static boolean canRefresh(String token) {
        Claims claims = getTokenClaims(token);
        long create = claims.getIssuedAt().getTime();
        long expire = claims.getExpiration().getTime();
        return new Date().getTime() - create > (expire - create) / 2;
    }

}
