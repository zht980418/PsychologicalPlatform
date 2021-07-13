package pers.qianyucc.qblog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale3DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale3VO;
import pers.qianyucc.qblog.service.Scale3Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷3相关接口")
@RestController
public class Scale3Controller {
    @Autowired
    private Scale3Service scale3Service;

    @ApiOperation("新增问卷3预约数据")
    @PostMapping("/scale3Info")
    public Results<String> postScale3(@ApiParam(name = "问卷3预约信息", value = "传入json格式", required = true)
                                      @RequestBody @Valid Scale3DTO scale3DTO) {
        scale3Service.insScale3(scale3DTO);
//        scale3Service.insScale3(scale3DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale3")
    @DeleteMapping("/scale3Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷3id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale3(@PathVariable int id) {
        scale3Service.deleteScale3(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale3Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷3id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale3(@ApiParam(name = "问卷3信息", value = "传入json格式", required = true)
                                     @RequestBody @Valid Scale3DTO scale3DTO ,
                                     @PathVariable int id){
        scale3Service.updateScale3(scale3DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷3列表")
    @GetMapping("/scale3Info")
    public Results<PageVO<Scale3VO>> getScale3s(
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
        return Results.ok(scale3Service.getAllScale3s(page,limit,search,field,start,end));
    }

    //    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale3Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷3id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale3VO>> getAnsbyId(@ApiParam(name = "问卷3信息", value = "传入json格式", required = true)
                                              @PathVariable int id){
        return  Results.ok(scale3Service.getAnsbyId(id));
    }

    @ApiOperation("根据id获取建议")
    @GetMapping("/scale3Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷3id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷三信息",value = "传入json",required = true)
                                               @PathVariable int id){
        return  Results.ok(scale3Service.getResultbyId(id));
    }
}
