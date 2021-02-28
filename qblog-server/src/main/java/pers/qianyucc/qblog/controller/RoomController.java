package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.RoomDTO;
import pers.qianyucc.qblog.model.vo.RoomVO;
import pers.qianyucc.qblog.service.RoomService;

import javax.validation.Valid;
import java.util.List;

@Api("与咨询室相关接口")
@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @ApiOperation("新增咨询室预约数据")
    @PostMapping("/roomInfo")
    public Results<String> postRoom(@ApiParam(name = "咨询室预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid RoomDTO roomDTO) {
        roomService.insRoom(roomDTO);
//        roomService.insRoom(roomDTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除room")
    @DeleteMapping("/roomInfo/{roomid}")
    @ApiImplicitParam(name = "roomid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results deleteRoom(@PathVariable String roomid) {
        roomService.deleteRoom(roomid);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/roomInfo/{roomid}")
    @ApiImplicitParam(name = "roomid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results<String> putRoom(@ApiParam(name = "咨询室信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid RoomDTO roomDTO ,
                                   @PathVariable String roomid){
        roomService.updateRoom(roomDTO, roomid);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取咨询室列表")
    @GetMapping("/roomInfo")
    @ApiImplicitParam(name = "roomid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results<List<RoomVO>> getRooms() {
        return Results.ok(roomService.getAllRooms());
    }
}
