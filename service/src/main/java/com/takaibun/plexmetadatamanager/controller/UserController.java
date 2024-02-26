package com.takaibun.plexmetadatamanager.controller;

import com.takaibun.plexmetadatamanager.http.req.UserLoginDto;
import com.takaibun.plexmetadatamanager.http.req.UserUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.UserLoginResp;
import com.takaibun.plexmetadatamanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author takaibun
 * @since 2024/02/24
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     *
     * @param userLoginDto 登录信息
     * @return 登录响应
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResp> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    /**
     * 修改密码
     *
     * @param userUpdateDto 修改密码信息
     * @return 修改密码响应
     */
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserUpdateDto userUpdateDto) {
        userService.update(userUpdateDto);
        return ResponseEntity.ok().build();
    }
}
