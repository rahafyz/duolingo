package com.mapsa.duolingo.user;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    IUserService userService;
    UserMapper mapper;
    UserLoginMapper loginMapper;


    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        UserDto newUser = mapper.toDto(userService.save(mapper.toEntity(userDto)));
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/signin")
    public String login(@RequestParam String username,@RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping(value = "/delete account")
    public ResponseEntity<HttpStatus> deleteAccount(@RequestParam Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
