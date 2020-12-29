package pers.qianyucc.qblog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.vo.*;
import pers.qianyucc.qblog.service.*;

/**
 * @author lijing
 * @e-mail 1413979079@qq.com
 * @date 2020-06-28 22:23
 * @description 通用接口
 */
@Api("通用接口")
@RestController
public class CommController {
    @Autowired
    private CommService commService;

    @ApiOperation("检查服务端是否正常")
    @GetMapping("/ping")
    public Results ping() {
        //throw new BlogException(ErrorInfoEnum.MISSING_PARAMETERS);
        return Results.ok("欢迎访问QBlog API", null);
    }

    @ApiOperation("获取博客信息")
    @GetMapping("/info")
    public Results<BlogInfoVO> info() {
        BlogInfoVO blogInfo = commService.getBlogInfo();
        return Results.ok(blogInfo);
    }
}
