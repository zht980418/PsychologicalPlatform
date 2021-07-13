package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale2DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale2VO;
import pers.qianyucc.qblog.service.Scale2Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷2相关接口")
@RestController
public class Scale2Controller {
    @Autowired
    private Scale2Service scale2Service;

    @ApiOperation("新增问卷2预约数据")
    @PostMapping("/scale2Info")
    public Results<String> postScale2(@ApiParam(name = "问卷2预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid Scale2DTO scale2DTO) {
        scale2Service.insScale2(scale2DTO);
//        scale2Service.insScale2(scale2DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale2")
    @DeleteMapping("/scale2Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale2(@PathVariable int id) {
        scale2Service.deleteScale2(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale2Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale2(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid Scale2DTO scale2DTO ,
                                   @PathVariable int id){
        scale2Service.updateScale2(scale2DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷2列表")
    @GetMapping("/scale2Info")
    public Results<PageVO<Scale2VO>> getScale2s(
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
        return Results.ok(scale2Service.getAllScale2s(page,limit,search,field,start,end));
    }

//    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale2Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale2VO>> getAnsbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                     @PathVariable int id){
        return  Results.ok(scale2Service.getAnsbyId(id));
    }

    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale2Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                               @PathVariable int id){
        return  Results.ok(scale2Service.getResult(id));
    }
}
