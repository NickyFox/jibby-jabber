package com.jibbyjabber.service;

import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.user.User;
import com.jibbyjabber.model.dto.user.UserLogin;
import com.jibbyjabber.security.CustomUserDetailService;
import com.jibbyjabber.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserClient userClient;


    public AuthService(JwtTokenUtil jwtTokenUtil, CustomUserDetailService customUserDetailService, AuthenticationManager authenticationManager, UserClient userClient) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailService = customUserDetailService;
        this.authenticationManager = authenticationManager;
        this.userClient = userClient;
    }


    public String login(UserLogin userLogin) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        } catch (
                DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (
                BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        User user = userClient.getUserByEmail(userLogin.getEmail());
        final UserDetails userDetails = customUserDetailService.loadUserByUsername(userLogin.getEmail());
        return jwtTokenUtil.generateWebToken(userDetails, user);
    }
}
