package com.store.cosmetic.controller;

import com.store.cosmetic.dto.CustomerDTO;
import com.store.cosmetic.dto.NewCustomerInfoDTO;
import com.store.cosmetic.dto.RegisterFormDTO;
import com.store.cosmetic.dto.UserDTO;
import com.store.cosmetic.dto.login.AuthResponseDTO;
import com.store.cosmetic.dto.login.LoginDTO;
import com.store.cosmetic.exception.helper.Result;
import com.store.cosmetic.exception.helper.StatusCode;
import com.store.cosmetic.jwt.JwtGenerator;
import com.store.cosmetic.services.CustomerService;
import com.store.cosmetic.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthAPI {
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final JwtGenerator jwtGenerator;
    private final UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthResponseDTO authResponseDTO = jwtGenerator.generateToken(authentication);
        return new Result(true, StatusCode.SUCCESS, "Login success", authResponseDTO);
    }

    @PostMapping("/log-out")
    public Result logout() {
        jwtGenerator.resetCookie();
        return new Result(true, StatusCode.SUCCESS, "logout success");
    }

    @PostMapping("/refreshToken")
    public Result refreshToken(@CookieValue("refreshToken") String refreshToken) {
        AuthResponseDTO authResponseDTO = jwtGenerator.refreshToken(refreshToken);
        return new Result(true, StatusCode.SUCCESS, "Refresh token success", authResponseDTO);
    }

    @PostMapping("/register")
    public Result registerNewUser(@RequestBody RegisterFormDTO registerFormDTO) {
        UserDTO registeredUser = userService.registerNewUser(registerFormDTO);
        return new Result(true, StatusCode.SUCCESS, "Register new user success", registeredUser);
    }


    @PutMapping("/info")
    public Result changeCustomerInfo(@RequestBody NewCustomerInfoDTO newCustomerInfoDTO) {
        CustomerDTO changedCustomer = customerService.changeInfo(newCustomerInfoDTO);
        return new Result(true, StatusCode.SUCCESS, "Changed customer information success", changedCustomer);
    }
}
