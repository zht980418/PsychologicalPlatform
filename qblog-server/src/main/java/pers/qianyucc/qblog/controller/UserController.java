package pers.qianyucc.qblog.controller;

import cn.hutool.core.map.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.constants.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.service.*;

import javax.validation.*;
import java.util.*;

@Api("与用户相关的api接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Results<Map<String, Object>> login(@ApiParam(name = "用户登录信息", value = "传入json格式", required = true)
                                              @RequestBody @Valid UserDTO userDTO) {
        String token = userService.checkUsernamePassword(userDTO);
        return Results.ok("登录成功", MapUtil.of("token", token));
    }

    @PostMapping("/auth/logout")
    @ApiOperation("用户注销登录")
    public Results<Object> logout(@RequestAttribute("token") String token) {
        redisTemplate.opsForSet().add(JwtConstants.REDIS_KEY, token);
        return Results.ok("退出登录成功", null);
    }
}
