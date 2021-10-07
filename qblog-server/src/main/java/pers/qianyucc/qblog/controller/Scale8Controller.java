package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale8DTO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.Scale8VO;
import pers.qianyucc.qblog.service.Scale8Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Api("问卷2相关接口")
@RestController
public class Scale8Controller {
    @Autowired
    private Scale8Service scale8Service;

    @ApiOperation("新增问卷2预约数据")
    @PostMapping("/scale8Info")
    public Results<String> postScale8(@ApiParam(name = "问卷2预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid Scale8DTO scale8DTO) {
        String id = scale8Service.insScale8(scale8DTO);
//        scale8Service.insScale8(scale8DTO);
        return Results.ok("表单新增成功", id);
    }

    @ApiOperation("根据id删除scale8")
    @DeleteMapping("/scale8Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale8(@PathVariable String id) {
        scale8Service.deleteScale8(id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale8Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale8(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid Scale8DTO scale8DTO ,
                                   @PathVariable String id){
        scale8Service.updateScale8(scale8DTO, id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷2列表")
    @GetMapping("/scale8Info")
    public Results<PageVO<Scale8VO>> getScale8s(
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
        return Results.ok(scale8Service.getAllScale8s(page,limit,search,field,start,end));
    }

//    根据id获取量表测试结果
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale8Info/{id}")
    @ApiImplicitParam(name = "id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale8VO>> getAnsbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                     @PathVariable String id){
        return  Results.ok(scale8Service.getAnsbyId(id));
    }
//1334433412 21133 14331
    @ApiOperation("根据id获取量表结果")
    @GetMapping("/scale8Result/{id}")
    @ApiImplicitParam(name = "id", value = "问卷4id", required = true, dataType = "String", paramType = "path")
    public Results<List<String>> getResultbyId(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                               @PathVariable String id){
        return  Results.ok(scale8Service.getResult(id));
    }
}
