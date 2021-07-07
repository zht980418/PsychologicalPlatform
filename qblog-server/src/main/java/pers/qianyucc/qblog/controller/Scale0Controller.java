package pers.qianyucc.qblog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.dao.Scale0Mapper;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale0DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale0VO;
import pers.qianyucc.qblog.service.Scale0Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷0相关接口")
@RestController
public class Scale0Controller {
    @Autowired
    private Scale0Service scale0Service;

    @ApiOperation("新增问卷0预约数据")
    @PostMapping("/scale0Info")
    public Results<String> postScale0(@ApiParam(name = "问卷0预约信息", value = "传入json格式", required = true)
                                      @RequestBody @Valid Scale0DTO scale0DTO) {
        scale0Service.insScale0(scale0DTO);
//        scale0Service.insScale0(scale0DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale0")
    @DeleteMapping("/scale0Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷0id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale0(@PathVariable int id) {
        scale0Service.deleteScale0(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale0Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷0id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale0(@ApiParam(name = "问卷0信息", value = "传入json格式", required = true)
                                     @RequestBody @Valid Scale0DTO scale0DTO ,
                                     @PathVariable int id){
        scale0Service.updateScale0(scale0DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷0列表")
    @GetMapping("/scale0Info")
    public Results<PageVO<Scale0VO>> getScale0s(
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
        return Results.ok(scale0Service.getAllScale0s(page,limit,search,field,start,end));
    }

    //    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale0Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷0id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale0VO>> getAnsbyId(@ApiParam(name = "问卷0信息", value = "传入json格式", required = true)
                                              @PathVariable int id){
        return  Results.ok(scale0Service.getAnsbyId(id));
    }
}
