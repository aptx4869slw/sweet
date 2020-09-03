package com.song.sweet.service;

import com.song.sweet.mapper.UserMapper;
import com.song.sweet.model.User;
import com.song.sweet.model.enumeration.UserStatus;
import com.song.sweet.utils.GeneratorUtils;
import com.song.sweet.utils.JWTUtils;
import com.song.sweet.utils.PasswordHash;
import com.song.sweet.utils.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private UserMapper userMapper;

    private final String BLOG_MAIN_PAGE = "blog";

    private final String JUMP_PAGE = "world";

    /**
     * @return
     * @Title 登陆
     * @Description //登陆
     * @Author liwen
     * @Date 2019/5/16 15:48
     * @Param
     */
    public String login(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        User result = userMapper.findByUserName(user);
        if (result != null) {
            String password = result.getPassword();
            Boolean check = PasswordHash.validatePassword(user.getPassword(), password);
            if (check) {
                logger.info(result.getUsername() + UserStatus.LOGIN_SUCCESS.description() + LocalDateTime.now().format(GeneratorUtils.COMMON_DATE_DTF));
                // token信息保存在request域，随后保存在响应头
                String token = JWTUtils.createToken(user, Boolean.FALSE);
                request.setAttribute("currentUser", user);
                request.setAttribute(JWTUtils.TOKEN_HEADER, token);
                // token信息保存在session域，随后保存在响应头
                /*HttpSession session = request.getSession();
                session.setAttribute("token", token);*/
                return JUMP_PAGE;
            }
        }
        return UserStatus.LOGIN_FAILED.description();
    }

    /**
     * @return
     * @Title 注册
     * @Description //注册
     * @Author liwen
     * @Date 2019/5/16 15:48
     * @Param
     */
    public String register(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        User result = userMapper.findByUserName(user);
        if (result != null) {
            return UserStatus.USER_EXIST.description();
        } else {
            String encryptionPwd = PasswordHash.createHash(user.getPassword());
            user.setPassword(encryptionPwd);
            Integer num = userMapper.saveUser(user);
            if (num > 0) {
                return UserStatus.USER_REGISTER_SUCCESS.description();
            }
            return UserStatus.USER_REGISTER_ERROR.description();
        }
    }

    /**
     * @return
     * @Title 验证码
     * @Description //生成验证码图片
     * @Author liwen
     * @Date 2020/5/26 18:36
     * @Param
     */
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> codeMap = VerifyCodeUtils.generateCodeAndPic();
        //获得当前会话
        HttpSession session = request.getSession();
        //获得当前会话的ID
        String sessionId = session.getId();

        logger.info("Session：" + sessionId);
        logger.info("验证码：" + codeMap.get("code"));

        //将验证码存储到session中
        request.getSession().setAttribute(sessionId + "_verifyCode", codeMap.get("code"));

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            logger.debug("IOException:", e.getMessage());
        }
    }
}
