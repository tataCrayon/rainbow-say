package pers.crayon.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.crayon.user.constant.enums.ResponseCodeEnum;
import pers.crayon.user.model.dto.Result;
import pers.crayon.user.utils.ConfigUtil;
import pers.crayon.user.utils.MailUtil;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/26 11:31
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class MailController {
    @ResponseBody
    @RequestMapping(value = "/params_modify.do", produces = "text/html;charset=utf-8", method = RequestMethod.POST)
    public String params_modify(@RequestBody String data) {
        try {
            JSONObject jo = JSONObject.parseObject(data);
            ConfigUtil.setProperties(jo.entrySet());
            return JSON.toJSONString(new Result(ResponseCodeEnum.OK));
        } catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(ResponseCodeEnum.CONFIGE_IO));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(ResponseCodeEnum.CONFIG_URISYNTAX));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/params_sendTestMail.do", produces = "text/html;charset=utf-8", method = RequestMethod.POST)
    public String params_sendTestMail(String mail_host, int mail_port, String mail_username, String mail_password,
                                      String mail_from, String mail_to) {
        try {
            MailUtil.sendMailForTest(mail_host, mail_port, mail_username, mail_password, mail_from, mail_to);
            return JSON.toJSONString(new Result(ResponseCodeEnum.OK));
        } catch (MailAuthenticationException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(ResponseCodeEnum.MAIL_AUTHENTICATION_FAILURE));
        } catch (MailSendException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(ResponseCodeEnum.MESSAGE_SEND_FAILURE));
        } catch (MailParseException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Result(ResponseCodeEnum.MESSAGE_PARSE_FAILURE));
        }

    }
}
