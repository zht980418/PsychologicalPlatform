package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.FormDTO;
import pers.qianyucc.qblog.model.dto.RoomDTO;
import pers.qianyucc.qblog.model.vo.FormVO;
import pers.qianyucc.qblog.service.FormService;
import pers.qianyucc.qblog.service.RoomService;

import javax.validation.Valid;
import java.util.List;

@Api("与咨询表格相关接口")
@RestController
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private RoomService roomService;


    @ApiOperation("根据doctorId获取咨询表格")
    @GetMapping("/doctorOrder/{doctorid}")
    @ApiImplicitParam(name = "doctorid", value = "医生ID", required = true, dataType = "String", paramType = "path")
    public Results<List<FormVO>> getFormByDoctorId(@PathVariable String doctorid){
        List<FormVO> orderform = formService.findByDoctorId(doctorid);
        System.out.println("#############worktime"+orderform);
        return Results.ok(orderform);
    }
    @ApiOperation("根据orderId获取咨询表格")
    @GetMapping("/orderForm/{orderid}")
    @ApiImplicitParam(name = "orderid", value = "预约ID", required = true, dataType = "String", paramType = "path")
    public Results<List<FormVO>> getFormByorderId(@PathVariable String orderid){
        List<FormVO> orderform = formService.findByOrderId(orderid);
        System.out.println("#############worktime"+orderform);
        return Results.ok(orderform);
    }

    @ApiOperation("新增表单数据")
    @PostMapping("/orderForm")
    public Results<String> postForm(@ApiParam(name = "表单信息", value = "传入json格式", required = true)
                                       @RequestBody @Valid FormDTO formDTO) {
        formService.insForm(formDTO);
        formService.insRoom(formDTO);
        return Results.ok("表单新增成功", null);
    }
}
