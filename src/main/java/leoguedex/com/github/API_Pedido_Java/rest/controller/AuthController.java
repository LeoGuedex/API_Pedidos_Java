package leoguedex.com.github.API_Pedido_Java.rest.controller;

import leoguedex.com.github.API_Pedido_Java.rest.dto.EmailDto;
import leoguedex.com.github.API_Pedido_Java.security.JwtUtil;
import leoguedex.com.github.API_Pedido_Java.security.UserSS;
import leoguedex.com.github.API_Pedido_Java.service.AuthService;
import leoguedex.com.github.API_Pedido_Java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("acess-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto){
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }

}