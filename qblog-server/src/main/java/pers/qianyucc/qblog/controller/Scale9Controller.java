package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale9DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale9VO;
import pers.qianyucc.qblog.service.Scale9Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Api("问卷9相关接口")
@RestController
public class Scale9Controller {
    @Autowired
    private Scale9Service scale9Service;

    @ApiOperation("新增问卷2预约数据")
    @PostMapping("/scale9Info")
    public Results<String> postScale9(@ApiParam(name = "问卷2预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid Scale9DTO scale9DTO) {
        String id = scale9Service.insScale9(scale9DTO);
//        scale9Service.insScale9(scale9DTO);
        return Results.ok("表单新增成功", id);
    }

    @ApiOperation("根据id删除scale9")
    @DeleteMapping("/scale9Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale9(@PathVariable String id) {
        scale9Service.deleteScale9(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale9Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale9(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid Scale9DTO scale9DTO ,
                                   @PathVariable String id){
        scale9Service.updateScale9(scale9DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷2列表")
    @GetMapping("/scale9Info")
    public Results<PageVO<Scale9VO>> getScale9s(
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
    ) throws ParseException {
        return Results.ok(scale9Service.getAllScale9s(page,limit,search,field,start,end));
    }

//    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale9Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale9VO>> getAnsbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                     @PathVariable String id){
        return  Results.ok(scale9Service.getAnsbyId(id));
    }

    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale9Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                               @PathVariable String id){
        return  Results.ok(scale9Service.getResult(id));
    }
}
