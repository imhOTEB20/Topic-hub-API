package com.aluralatam.topichubapirest.controller;

import com.aluralatam.topichubapirest.domain.user.DTOAuthUser;
import com.aluralatam.topichubapirest.domain.user.User;
import com.aluralatam.topichubapirest.infra.security.DTOJWTToken;
import com.aluralatam.topichubapirest.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DTOJWTToken> authenticateUser(@RequestBody @Valid DTOAuthUser dtoAuthUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dtoAuthUser.username(),
                dtoAuthUser.password()
        );
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DTOJWTToken(jwtToken));
    }
}
