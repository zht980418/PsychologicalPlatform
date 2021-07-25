package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.qianyucc.qblog.dao.FormNameMapper;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.vo.FormNameVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.UserQuizInfoVO;
import pers.qianyucc.qblog.service.FormNameService;

@Api("用户获取咨询表")
@RestController
public class FormNameController {
    @Autowired
    private FormNameService formNameService;

    @ApiOperation("根据uid获取咨询列表")
    @GetMapping("/orderHistory")
    public Results<PageVO<FormNameVO>> getQuizByUid(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @ApiParam("UID")
            @RequestParam(required = false, defaultValue = "") String uid
    ){
        return Results.ok(formNameService.getAllFormbyUid(page,limit,uid));
    }
}
