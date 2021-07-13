package pers.qianyucc.qblog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale4DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale4VO;
import pers.qianyucc.qblog.service.Scale4Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷4相关接口")
@RestController
public class Scale4Controller {
    @Autowired
    private Scale4Service scale4Service;

    @ApiOperation("新增问卷4预约数据")
    @PostMapping("/scale4Info")
    public Results<String> postScale4(@ApiParam(name = "问卷4预约信息", value = "传入json格式", required = true)
                                      @RequestBody @Valid Scale4DTO scale4DTO) {
        scale4Service.insScale4(scale4DTO);
//        scale4Service.insScale4(scale4DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale4")
    @DeleteMapping("/scale4Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale4(@PathVariable int id) {
        scale4Service.deleteScale4(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale4Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale4(@ApiParam(name = "问卷4信息", value = "传入json格式", required = true)
                                     @RequestBody @Valid Scale4DTO scale4DTO ,
                                     @PathVariable int id){
        scale4Service.updateScale4(scale4DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷4列表")
    @GetMapping("/scale4Info")
    public Results<PageVO<Scale4VO>> getScale4s(
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
        return Results.ok(scale4Service.getAllScale4s(page,limit,search,field,start,end));
    }

    //    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale4Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale4VO>> getAnsbyId(@ApiParam(name = "问卷4信息", value = "传入json格式", required = true)
                                              @PathVariable int id){
        return  Results.ok(scale4Service.getAnsbyId(id));
    }

    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale4Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷4信息", value = "传入json格式", required = true)
                                              @PathVariable int id){
        return  Results.ok(scale4Service.getResult(id));
    }
}
