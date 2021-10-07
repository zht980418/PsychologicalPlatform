package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale6DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale6VO;
import pers.qianyucc.qblog.service.Scale6Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Api("问卷6相关接口")
@RestController
public class Scale6Controller {
    @Autowired
    private Scale6Service scale6Service;

    @ApiOperation("新增问卷2预约数据")
    @PostMapping("/scale6Info")
    public Results<String> postScale6(@ApiParam(name = "问卷2预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid Scale6DTO scale6DTO) {
        String id = scale6Service.insScale6(scale6DTO);
//        scale6Service.insScale6(scale6DTO);
        return Results.ok("表单新增成功", id);
    }

    @ApiOperation("根据id删除scale6")
    @DeleteMapping("/scale6Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale6(@PathVariable String id) {
        scale6Service.deleteScale6(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale6Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale6(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid Scale6DTO scale6DTO ,
                                   @PathVariable String id){
        scale6Service.updateScale6(scale6DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷2列表")
    @GetMapping("/scale6Info")
    public Results<PageVO<Scale6VO>> getScale6s(
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
        return Results.ok(scale6Service.getAllScale6s(page,limit,search,field,start,end));
    }

//    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale6Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale6VO>> getAnsbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                     @PathVariable String id){
        return  Results.ok(scale6Service.getAnsbyId(id));
    }

    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale6Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                               @PathVariable String id){
        return  Results.ok(scale6Service.getResult(id));
    }
}
