package pers.qianyucc.qblog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale1DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale1VO;
import pers.qianyucc.qblog.service.Scale1Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷1相关接口")
@RestController
public class Scale1Controller {
    @Autowired
    private Scale1Service scale1Service;

    @ApiOperation("新增问卷1预约数据")
    @PostMapping("/scale1Info")
    public Results<String> postScale1(@ApiParam(name = "问卷1预约信息", value = "传入json格式", required = true)
                                      @RequestBody @Valid Scale1DTO scale1DTO) {
        scale1Service.insScale1(scale1DTO);
//        scale1Service.insScale1(scale1DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale1")
    @DeleteMapping("/scale1Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷1id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale1(@PathVariable int id) {
        scale1Service.deleteScale1(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale1Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷1id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale1(@ApiParam(name = "问卷1信息", value = "传入json格式", required = true)
                                     @RequestBody @Valid Scale1DTO scale1DTO ,
                                     @PathVariable int id){
        scale1Service.updateScale1(scale1DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷1列表")
    @GetMapping("/scale1Info")
    public Results<PageVO<Scale1VO>> getScale1s(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @ApiParam("关键词")
            @RequestParam(required = false, defaultValue = "") String search,
            @ApiParam("字段")
            @RequestParam(required = false, defaultValue = "orderId") String field,
            @ApiParam("关键词")
            @RequestParam(required = false, defaultValue = "") String start,
            @ApiParam("字段")
            @RequestParam(required = false, defaultValue = "") String end
    ) {
        return Results.ok(scale1Service.getAllScale1s(page,limit,search,field,start,end));
    }

    //    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale1Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷1id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale1VO>> getAnsbyId(@ApiParam(name = "问卷1信息", value = "传入json格式", required = true)
                                              @PathVariable int id){
        return  Results.ok(scale1Service.getAnsbyId(id));
    }
}
