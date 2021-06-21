package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;

import pers.qianyucc.qblog.model.dto.DoctorDTO;
import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.service.DoctorService;

import javax.validation.Valid;
import java.util.List;


@Api("与预约相关的api接口")
@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    //    增
    @ApiOperation("新增医生数据")
    @PostMapping("/doctor")
    public Results<String> postDoctor(@ApiParam(name = "医生信息", value = "传入json格式", required = true)
                                     @RequestBody @Valid DoctorDTO doctorDTO) {
        doctorService.insDoctor(doctorDTO);
        return Results.ok("表单新增成功", null);
    }
    //    删
    @ApiOperation("根据id删除doctor")
    @DeleteMapping("/doctor/{doctorid}")
    @ApiImplicitParam(name = "id", value = "医生id", required = true, dataType = "int", paramType = "path")
    public Results deleteDoctor(@PathVariable String doctorid) {
        doctorService.deleteDoctor(doctorid);
        return Results.ok("删除成功", null);
    }
    //    改
    @ApiOperation("根据id修改医生")
    @PutMapping("/doctor/{doctorid}")
    @ApiImplicitParam(name = "id", value = "医生id", required = true, dataType = "String", paramType = "path")
    public Results<String> putDoctor(@ApiParam(name = "医生信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid DoctorDTO doctorDTO ,
                                    @PathVariable String doctorid){
        doctorService.updateDoctor(doctorDTO, doctorid);
        return Results.ok("表单修改成功", null);
    }
    //    批量查
    @ApiOperation("获取医生列表")
    @GetMapping("/order/doctorlist")
    public Results<PageVO<DoctorVO>> getDoctor(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @ApiParam("关键词")
            @RequestParam(required = false, defaultValue = "") String search,
            @ApiParam("字段")
            @RequestParam(required = false, defaultValue = "doctorId") String field
    ) {
        return Results.ok(doctorService.getAllDoctors(page,limit,search,field));
    }
    //    id查
    @ApiOperation("根据id获取医生")
    @GetMapping("/doctor/{doctorid}")
    @ApiImplicitParam(name="id", value = "医生id", required = true,dataType = "int", paramType = "path")
    public Results<DoctorVO> getDoctorByid(@PathVariable String doctorid){
        return Results.ok(doctorService.getDoctorByid(doctorid));
    }

}
