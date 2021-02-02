package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.qianyucc.qblog.model.comm.Results;

import pers.qianyucc.qblog.model.vo.DoctorVO;
import pers.qianyucc.qblog.service.DoctorService;

import java.util.List;


@Api("与预约相关的api接口")
@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @ApiOperation("获取工作时间")
    @GetMapping("/order/doctordate/{name}")
    @ApiImplicitParam(name = "name", value = "医生姓名", required = true, dataType = "String", paramType = "path")
    public Results<List<DoctorVO>> getDoctordate(@PathVariable String name){
        List<DoctorVO> doctortime = doctorService.findByName(name);
        return Results.ok(doctortime);
    }
}
