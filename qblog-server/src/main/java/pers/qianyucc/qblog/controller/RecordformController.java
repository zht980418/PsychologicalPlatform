package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
                                          @RequestBody RecordformDTO recordformDTO){
        System.out.println(recordformDTO);
        int size = recordformService.getNumofRecordform();
        int consultno=1;
        if(size!=0){
            consultno=recordformService.getLatestID(size);
            consultno++;
        }
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
    @GetMapping("/records")
    public Results<List<RecordformVO>> getRecordforms(){return Results.ok(recordformService.getAllrecordform());}
//    通过consultno查询单条咨询记录
    @ApiOperation("根据id查找咨询记录")
    @GetMapping("/recordByconsultno/{consultno}")
    @ApiImplicitParam(name = "consultno", value = "咨询记录id", required = true,dataType = "int",paramType = "path")
    public Results<RecordformVO> getRecordformByid(@PathVariable int consultno){
        return Results.ok(recordformService.getRecordform(consultno));}
//    通过userid查询咨询记录
    @ApiOperation("根据userid查找咨询记录")
    @GetMapping("/recordByuserid/{userid}")
    @ApiImplicitParam(name = "userid", value = "用户id", required = true,dataType = "String",paramType = "path")
    public Results<List<RecordformVO>> getRecordformByuserid(@PathVariable String userid){
        return Results.ok(recordformService.getRecordformsByuserid(userid));}

    //    查询用户咨询记录条数
    @ApiOperation("根据userid查询咨询记录条数")
    @GetMapping("/recordtimes/{userid}")
    @ApiImplicitParam(name = "userid", value = "用户id", required = true,dataType = "String",paramType = "path")
    public Results<Integer>getRecordtimesByuserid(@PathVariable String userid){
        return Results.ok(recordformService.getRecordtimesByuserid(userid));
    }
//    根据userid查询咨询记录
    @ApiOperation("根据userid查询所有相关记录")
    @GetMapping("/record")
    @ApiImplicitParam(name = "userid", value = "用户id", required = true,dataType = "int",paramType = "path")
    public Results<List<RecordformVO>> getRecordformByid(@RequestParam(value = "user",required = true) String userid){ return Results.ok(recordformService.getRecordformsByuserid(userid));}

}
