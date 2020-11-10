package pers.crayon.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.crayon.user.exception.UserOperateException;
import pers.crayon.user.model.dto.AdminDto;
import pers.crayon.user.model.pojo.Admin;
import pers.crayon.user.repository.AdminRepository;

import javax.transaction.Transactional;
import java.util.UUID;

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

    @Autowired
    private RedisCacheService redisCacheService;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    public String loginAdmin(AdminDto adminDto) {
        String redirectHTML = "homepage";

        if (adminDto == null) {
            throw new UserOperateException("用户名或密码不能为空");
        }
        if (StringUtils.isEmpty(adminDto.getName()) || StringUtils.isEmpty(adminDto.getPassword())) {
            throw new UserOperateException("用户名或密码不能为空");
        }
        // 可以用反射直接获取 >_< 太好了！
        String violateName = adminDto.getClass().getName();
        // 还是老实用方法吧 o(╥﹏╥)o
        String adminName = adminDto.getName();
        String pwd = adminDto.getPassword();
        /*
         检查 redis缓存和 cookie是否存在
         不存在就查询数据库并设置
         存在的话，刷新过期时间
         然后登录验证成功后，设置对应cookie存储在本地，下次可以直接登录
         */
        if (redisCacheService.hasKey(adminName)) {
            String truePwd = (String) redisCacheService.get(adminName);
            if (!truePwd.equals(pwd)) {
                throw new UserOperateException("用户名与密码不对应");
            }
            System.out.println("有缓存，当前密码为:" + truePwd + ",设置缓存时间为100秒");
            // 刷新缓存有效时间
            redisCacheService.set(adminName, pwd, 100L);
        } else {
            Admin admin = adminRepository.getAdminByName(adminName);
            String truePwd = admin.getPassword();
            if (!truePwd.equals(pwd.trim())) {
                throw new UserOperateException("用户名与密码不对应");
            }
            System.out.println("无缓存，当前密码为:" + truePwd + ",设置缓存时间为100秒");
            redisCacheService.set(adminName, pwd, 100L);
            // 生成 token，后续可以用 JWT 替换生成
            String token = UUID.randomUUID().toString();
            // 存入Redis的时候清楚用户信息，防止泄露
            admin.setName(null);
            admin.setPassword(null);
            redisCacheService.set(REDIS_USER_SESSION_KEY + ":" + token, admin);
            /*
            user 已经是持久化对象，被保存在session缓存当中，若user又重新修改属性值，那么在提交事务时，
            此时 hibernate 对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，
            如果两个对象相同，则不会发送update语句，否则会发出update语句。
             */
            // 重新设置用户信息
            admin.setName(adminName);
            admin.setPassword(truePwd);
            // 设置session 过期时间
            redisCacheService.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
            // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
            //CookieUtils.setCookie(request, response, "USER_TOKEN", token);
        }
        return redirectHTML;

    }

    public void logOut(String token) {
        redisCacheService.del(REDIS_USER_SESSION_KEY + ":" + token);

    }

    public Admin getAdminByToken(String token) {
        String json = (String) redisCacheService.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        // 不为空则更新过期时间
        redisCacheService.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户对象 把JSON转换为对应pojo对象
        //JsonUtils.jsonToPojo(json, Admin.class)
        return null;
    }
    //TODO   login登录的时候 生成 Token,logout 注销的时候，del 和用户持久化对象保存在 seesion 中的 token
    //TODO   set(REDIS_USER_SESSION_KEY + ":" + token,user) del(REDIS_USER_SESSION_KEY + ":" + token);
    //TODO 设置 cookie 和 user .setPassword .setUserName


}
