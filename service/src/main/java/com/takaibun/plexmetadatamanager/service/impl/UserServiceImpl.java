package com.takaibun.plexmetadatamanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takaibun.plexmetadatamanager.entity.UserEntity;
import com.takaibun.plexmetadatamanager.exception.UsernameOrPasswordIncorrectException;
import com.takaibun.plexmetadatamanager.http.req.UserLoginDto;
import com.takaibun.plexmetadatamanager.http.req.UserUpdateDto;
import com.takaibun.plexmetadatamanager.http.resp.UserLoginResp;
import com.takaibun.plexmetadatamanager.mapper.UserMapper;
import com.takaibun.plexmetadatamanager.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务实现类
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * token有效期（1小时）
     */
    private final long TOKEN_EXPIRATION_MILLISION = 60 * 60 * 1000L;

    private final UserMapper userMapper;
    /**
     * 根据随机生成的UUID配置secret
     */
    private SecretKey secret;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostConstruct
    public void init() {
        UserEntity userEntity = userMapper.selectOne(new QueryWrapper<>());
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setUsername("admin");
            userEntity.setPassword("admin");
            userMapper.insert(userEntity);
        }
        secret = Keys.hmacShaKeyFor(String.format("%s-%s", UUID.randomUUID(), userEntity.getPassword()).getBytes());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user is not exists");
        }
        return userEntity;
    }

    @Override
    public UserLoginResp login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null || !userEntity.getPassword().equals(userLoginDto.getPassword())) {
            throw new UsernameOrPasswordIncorrectException();
        }
        UserLoginResp userLoginResp = new UserLoginResp();
        userLoginResp.setToken(buildTokenByUsername(username));
        return userLoginResp;
    }

    @Override
    public void logout() {
        init();
    }

    @Override
    public void update(UserUpdateDto userUpdateDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userUpdateDto, userEntity);
        userMapper.update(userEntity, new QueryWrapper<>());
        logout();
    }


    @Override
    public boolean validateToken(String token) {
        return token != null && !isTokenExpired(token);
    }

    @Override
    public String refresh(String token) {
        String username = getPayloadFromToken(token).getSubject();
        return buildTokenByUsername(username);
    }


    public String buildTokenByUsername(String username) {
        Map<String, Object> claims = new HashMap<>(6) {{
            put("iss", "msm");
            put("sub", username);
            put("exp", new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MILLISION));
            put("aud", "internal use");
            put("iat", new Date());
            put("jti", UUID.randomUUID().toString());
        }};
        return generatorToken(claims);
    }

    private String generatorToken(Map<String, Object> claims) {
        return Jwts.builder().claims(claims).signWith(secret, Jwts.SIG.HS256).compact();
    }

    private Claims getPayloadFromToken(String token) {
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
    }

    private boolean isTokenExpired(String token) {
        return getPayloadFromToken(token).getExpiration().before(new Date());
    }


}
