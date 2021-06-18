package com.jibbyjabber.controller;

import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.user.User;
import com.jibbyjabber.security.CustomUserDetailService;
import com.jibbyjabber.security.JwtTokenUtil;
import com.jibbyjabber.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private AuthService authService;

    private final UserClient userClient;

    public AuthController(UserClient userClient) {
        this.userClient = userClient;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        User user = userClient.getUserByEmail(authenticationRequest.getEmail());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateWebToken(userDetails, user);

        return ResponseEntity.ok(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
