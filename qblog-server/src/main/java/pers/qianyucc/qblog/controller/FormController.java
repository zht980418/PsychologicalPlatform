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
import pers.qianyucc.qblog.service.UserinfoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api("与咨询表格相关接口")
@RestController
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    private UserinfoService userinfoService;
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

    @ApiOperation("根据roomid获取咨询表格")
    @GetMapping("/roomOrder/{roomid}")
    @ApiImplicitParam(name = "roomid", value = "房间ID", required = true, dataType = "String", paramType = "path")
    public Results<List<FormVO>> getFormByroomId(@PathVariable String roomid){
        List<FormVO> formVOList = formService.findByRoomId(roomid);
        return Results.ok(formVOList);
    }
    @ApiOperation("根据uid获取咨询表格")
    @GetMapping("/orderHistory/{uid}")
    @ApiImplicitParam(name = "uid", value = "uid", required = true, dataType = "String", paramType = "path")
    public Results<List> getFormsByuid(@PathVariable String uid){
        ArrayList res = new ArrayList();
        List<Map<String, Object>> formVOList =formService.findByUid(uid);
        List doctoridList = formService.findDoctoridByUid(uid);
        for(int i=0;i<formVOList.size();i++){
            String doctorname = userinfoService.getUserName(doctoridList.get(i).toString());
            if(!formVOList.get(i).get("roomid").equals(null)){
                String roomname = roomService.getRoomNameByRoomID(formVOList.get(i).get("roomid").toString());
                formVOList.get(i).put("roomname",roomname);
            }
//            System.out.println("***********");
//            System.out.println(doctorname);
//            System.out.println(formVOList.get(i));
            formVOList.get(i).put("doctorname",doctorname);
            res.add(formVOList.get(i));
        }
//        System.out.println(res);
        return Results.ok(res);
    }

    @ApiOperation("新增表单数据")
    @PostMapping("/orderForm")
    public Results<String> postForm(@ApiParam(name = "表单信息", value = "传入json格式", required = true)
                                       @RequestBody @Valid FormDTO formDTO) {
        formService.insForm(formDTO);
//        formService.insRoom(formDTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除form")
    @DeleteMapping("/orderForm/{orderid}")
    @ApiImplicitParam(name = "orderid", value = "预约id", required = true, dataType = "String", paramType = "path")
    public Results deleteForm(@PathVariable String orderid) {
        formService.deleteForm(orderid);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/orderForm/{orderid}")
    @ApiImplicitParam(name = "orderid", value = "预约id", required = true, dataType = "String", paramType = "path")
    public Results<String> putForm(@ApiParam(name = "表单信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid FormDTO formDTO ,
                                   @PathVariable String orderid){
        formService.updateForm(formDTO, orderid);
        return Results.ok("表单修改成功", null);
    }

}
