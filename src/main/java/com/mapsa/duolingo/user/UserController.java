package com.mapsa.duolingo.user;


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

    @PutMapping(value = "/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDTO){
        UserDto currentUser = loginMapper.toDto(userService.login(loginMapper.toEntity(userDTO)));
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping(value = "/delete account")
    public ResponseEntity<HttpStatus> deleteAccount(@RequestParam Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
