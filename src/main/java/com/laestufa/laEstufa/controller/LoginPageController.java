package com.laestufa.laEstufa.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laestufa.laEstufa.model.JwtUser;
import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import com.laestufa.laEstufa.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/login")
public class LoginPageController {

    @Autowired
    private UserRepository userRepository;

    private JwtGenerator jwtGenerator;

    public LoginPageController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String getLoginPage(HttpSession session) {
        session.getId();
        return "login";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public String getToken(@RequestBody final String user) {
//        return HttpStatus.CREATED.toString(); + weryfikacja itp
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserModel userModel = objectMapper.readValue(user, UserModel.class);
            return jwtGenerator.generate(getJwtUserData(userModel)).toString();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return HttpStatus.I_AM_A_TEAPOT.toString();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String setLoginUserSession(@RequestBody List cred,
                                      HttpServletRequest request) {

        request.getSession().setAttribute("email", cred.get(0));
        request.getSession().setAttribute("password", cred.get(1));
        return request.getSession().getId();
    }

    private JwtUser getJwtUserData(UserModel userModel) {
        JwtUser jwtUser = new JwtUser();
        Map res = userRepository.getJwtUserDetail(userModel.getEmail());
        jwtUser.setId(res.get("id").toString());
        jwtUser.setUserName(res.get("email").toString());
        jwtUser.setRole(res.get("role").toString());
        return jwtUser;
    }
}
