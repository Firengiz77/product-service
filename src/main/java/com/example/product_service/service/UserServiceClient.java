package com.example.product_service.service;

import com.example.product_service.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserServiceClient {
    private RestTemplate restTemplate;

    public UserResponseDto getUserRoles(String username) {
        String url = "http://user-service/api/users/" + username + "/roles"; // Adjust the endpoint as needed
        return restTemplate.getForObject(url,    public UserDto update(@RequestBody User user, HttpServletRequest
        httpServletRequest) {
            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            String token = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
            }
            return userService.updateUser(user,token);
        }UserRolesDto.class);
    }
}