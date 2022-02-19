package com.mapsa.duolingo.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    IUserService userService;
    UserMapper mapper;

    public UserController(IUserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PutMapping
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDto){
        UserDTO newUser = mapper.toDTO(userService.save(mapper.toEntity(userDto)));
        return ResponseEntity.ok(newUser);
    }
}
