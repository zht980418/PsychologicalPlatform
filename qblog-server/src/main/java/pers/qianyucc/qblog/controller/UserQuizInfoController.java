package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.UserQuizInfoVO;
import pers.qianyucc.qblog.service.UserQuizInfoService;

@Api("用户问卷信息相关接口")
@RestController
public class UserQuizInfoController {
    @Autowired
    private UserQuizInfoService userQuizInfoService;

    @ApiOperation("根据uid获取quiz列表")
    @GetMapping("/QuizByUid")
    public Results<PageVO<UserQuizInfoVO>> getQuizByUid(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @ApiParam("UID")
            @RequestParam(required = false, defaultValue = "") String uid
    ){
        return Results.ok(userQuizInfoService.getAllQuizbyUid(page,limit,uid));
    }
}
