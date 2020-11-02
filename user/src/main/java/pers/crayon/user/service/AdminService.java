package pers.crayon.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pers.crayon.user.repository.AdminRepository;

import javax.transaction.Transactional;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/9 10:19
 * @since JDK 1.8
 */
@Service
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    //TODO   login登录的时候 生成 Token,logout 注销的时候，del 和用户持久化对象保存在 seesion 中的 token
    //TODO   set(REDIS_USER_SESSION_KEY + ":" + token,user) del(REDIS_USER_SESSION_KEY + ":" + token);
    //TODO 设置 cookie 和 user .setPassword .setUserName


    //
    //@Autowired
    //private JedisClient jedisClient;
    //
    //public ItdragonResult userLogin(String account, String password,
    //                                HttpServletRequest request, HttpServletResponse response) {
    //    // 判断账号密码是否正确
    //    Admin user = adminRepository.findByAccount(account);
    //    if (!ItdragonUtils.decryptPassword(user, password)) {
    //        return ItdragonResult.build(400, "账号名或密码错误");
    //    }
    //    // 生成token
    //    String token = UUID.randomUUID().toString();
    //    // 清空密码和盐避免泄漏
    //    String userPassword = user.getPassword();
    //    String userSalt = user.getSalt();
    //    user.setPassword(null);
    //    user.setSalt(null);
    //    // 把用户信息写入 redis
    //    jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
    //    // user 已经是持久化对象，被保存在session缓存当中，若user又重新修改属性值，那么在提交事务时，此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，否则会发出update语句。
    //    user.setPassword(userPassword);
    //    user.setSalt(userSalt);
    //    // 设置 session 的过期时间
    //    jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
    //    // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
    //    CookieUtils.setCookie(request, response, "USER_TOKEN", token);
    //    // 返回token
    //    return ItdragonResult.ok(token);
    //}
    //
    //public void logout(String token) {
    //    jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
    //}
    //
    //public ItdragonResult queryUserByToken(String token) {
    //    // 根据token从redis中查询用户信息
    //    String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
    //    // 判断是否为空
    //    if (StringUtils.isEmpty(json)) {
    //        return ItdragonResult.build(400, "此session已经过期，请重新登录");
    //    }
    //    // 更新过期时间
    //    jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
    //    // 返回用户信息
    //    return ItdragonResult.ok(JsonUtils.jsonToPojo(json, Admin.class));
    //}

}
