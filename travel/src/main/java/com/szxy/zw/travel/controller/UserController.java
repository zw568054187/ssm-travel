package com.szxy.zw.travel.controller;

import com.szxy.zw.travel.exception.UserExistsException;
import com.szxy.zw.travel.pojo.ResultInfo;
import com.szxy.zw.travel.pojo.User;
import com.szxy.zw.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zhangwei
 * @date 2018/12/4 9:03
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("register")
    @ResponseBody
    public ResultInfo register(User user, @RequestParam("check") String check, HttpSession session) {
        ResultInfo resultInfo;
        try {
            String checkCode = (String) session.getAttribute("check");
            if (!check.equalsIgnoreCase(checkCode)) {
                resultInfo = new ResultInfo(false,null,"验证码错误");
            } else {
                userService.register(user);
                resultInfo = new ResultInfo(true, null, null);
            }
        } catch (UserExistsException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器发生异常,请稍后重试!");
        }

        return resultInfo;
    }
}
