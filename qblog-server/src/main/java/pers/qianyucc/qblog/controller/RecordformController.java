package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.RecordformDTO;
import pers.qianyucc.qblog.model.vo.RecordformVO;
import pers.qianyucc.qblog.service.RecordformService;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
public class RecordformController {
    @Autowired
    private RecordformService recordformService;
//    增加咨询记录
    @ApiOperation("新增咨询记录")
    @PostMapping("/record")
    public Results<String> postRecordform(@ApiParam(name="咨询记录", value = "传入json格式",required = true)
                                          @RequestBody @Valid RecordformDTO recordformDTO){
        int size = recordformService.getNumofRecordform();
        System.out.println(size);
        int consultno=recordformService.getLatestID(size);
        consultno++;
        System.out.println(consultno);
        recordformService.insRecordform(recordformDTO, consultno);
        return Results.ok("咨询记录新增成功",null);
    }
//    删除咨询记录
    @ApiOperation("根据id删除咨询记录")
    @DeleteMapping("/record/{consultno}")
    @ApiImplicitParam(name = "consultno", value = "咨询记录id", required = true, dataType = "int", paramType = "path")
    public Results deleteRecordform(@PathVariable int consultno){
        recordformService.deleteRecordform(consultno);
        return Results.ok("咨询记录删除成功",null);
    }
//    修改咨询记录
    @ApiOperation("根据id修改咨询记录")
    @PutMapping("/record/{consultno}")
    @ApiImplicitParam(name = "consultno", value = "咨询记录id", required = true,dataType = "int",paramType = "path")
    public Results<String> putRecordform(@ApiParam(name="咨询记录",value = "传入json",required = true)
                                         @RequestBody @Valid RecordformDTO recordformDTO,
                                         @PathVariable int consultno){
        recordformService.updateRecordform(recordformDTO,consultno);
        return Results.ok("咨询记录修改成功",null);
    }
//    查询全部咨询记录
    @ApiOperation("获取全部咨询记录")
    @GetMapping("/record")
    public Results<List<RecordformVO>> getRecordforms(){return Results.ok(recordformService.getAllrecordform());}
//    查询单条咨询记录
    @ApiOperation("根据id查找咨询记录")
    @GetMapping("/record/{consultno}")
    @ApiImplicitParam(name = "consultno", value = "咨询记录id", required = true,dataType = "int",paramType = "path")
    public Results<RecordformVO> getRecordformByid(@PathVariable int consultno){ return Results.ok(recordformService.getRecordform(consultno));}
}
