package com.mysite.jpa_shoppingmall;

import com.mysite.jpa_shoppingmall.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public UserDto test() {
        UserDto userDto = new UserDto();
        userDto.setAge(20);
        userDto.setName("seung");
        log.info("info of userDto: {}", userDto);
        return userDto;
    }
}
