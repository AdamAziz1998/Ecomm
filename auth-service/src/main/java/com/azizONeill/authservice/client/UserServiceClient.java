package com.azizONeill.authservice.client;


import com.azizONeill.authservice.dto.RegisterDto;
import com.azizONeill.authservice.dto.UserDto;
import com.azizONeill.authservice.dto.RegisterRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ConfigurationProperties(prefix = "application.config.user-url")
@FeignClient(name = "user-service", url = "${application.config.product-url}")
public interface UserServiceClient {
    @PostMapping("/save")
    ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest request);

    @GetMapping("/getUserByUsername/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
