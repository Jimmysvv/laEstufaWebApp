package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.JwtUser;
import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.security.JwtGenerator;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/login")
public class LoginPageController {

    @Autowired
    private UserModelService userModelService;

    private JwtGenerator jwtGenerator;

    public LoginPageController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Map getToken(@RequestBody final UserModel user, HttpSession httpSession) {
        if (userModelService.verifyUser(user)) {
            Map<String, String> map = new HashMap<>();
            try {
                JwtUser jwtUser = getJwtUserData(user);
                String token = jwtGenerator.generate(jwtUser);
                userModelService.setDbUserSession(jwtUser.getId(), httpSession.getId(), token);
                map.put("Token", token);
                map.put("Session", httpSession.getId());
                map.put("UserId", jwtUser.getId());
                map.put("UserLogin", jwtUser.getUserLogin());
                return map;
            } catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private JwtUser getJwtUserData(UserModel user) {
        JwtUser jwtUser = new JwtUser();
        Map res = userModelService.getJwtUserDetail(user);
        jwtUser.setId(res.get("id").toString());
        jwtUser.setUserName(res.get("email").toString());
        jwtUser.setUserLogin(res.get("login").toString());
        jwtUser.setRole(res.get("role").toString());
        return jwtUser;
    }
}
