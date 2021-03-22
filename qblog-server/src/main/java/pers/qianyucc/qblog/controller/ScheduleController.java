package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.exception.BlogException;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.ScheduleDTO;
import pers.qianyucc.qblog.model.vo.ScheduleVO;
import pers.qianyucc.qblog.service.ScheduleService;

import javax.validation.Valid;
import java.util.List;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

@Api
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("新增日程表数据")
    @PostMapping("/schedule")
    public Results<String> postSchedule(@ApiParam(name = "咨询室预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid ScheduleDTO scheduleDTO) {
        scheduleService.insSchedule(scheduleDTO);
//        scheduleService.insSchedule(scheduleDTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除日程表")
    @DeleteMapping("/schedule/{appid}")
    @ApiImplicitParam(name = "appid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results deleteSchedule(@PathVariable String appid) {
        scheduleService.deleteSchedule(appid);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改日程表")
    @PutMapping("/schedule/{appid}")
    @ApiImplicitParam(name = "appid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results<String> putSchedule(@ApiParam(name = "咨询室信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid ScheduleDTO scheduleDTO ,
                                   @PathVariable String appid){
        String roomid = scheduleDTO.getRoomid();
        String start = scheduleDTO.getStart();
        String daysofweek = scheduleDTO.getDaysofweek();
        List<String> StartList = scheduleService.getStartByRoomID(roomid);
        List<String> DaysofweekList = scheduleService.getDaysofweekByRoomID(roomid);
        for(int i=0;i<StartList.size();i++){
            if(StartList.get(i).equals(start)&&DaysofweekList.get(i).equals(daysofweek))
                throw new BlogException(CONFLICT_TIME);
        }
        scheduleService.updateSchedule(scheduleDTO, appid);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取日程表列表")
    @GetMapping("/schedule")
    @ApiImplicitParam(name = "scheduleid", value = "咨询室id", required = true, dataType = "String", paramType = "path")
    public Results<List<ScheduleVO>> getSchedules() {
        return Results.ok(scheduleService.getAllSchedules());
    }

    @ApiOperation("根据roomid获取日程表")
    @GetMapping("/schedule/{roomid}")
    @ApiImplicitParam(name = "schedule", value = "日程表", required = true, dataType = "String", paramType = "path")
    public Results<List<ScheduleVO>> getSchedulesByRoomID(@PathVariable String roomid){
        return Results.ok(scheduleService.getSchedulesByRoomID(roomid));}

    @ApiOperation("根据医生id获取医生日程限制信息")
    @GetMapping("/doctorCon/{doctorid}")
    @ApiImplicitParam(name = "doctorid",value = "医生id",required = true,dataType = "String",paramType = "path")
    public Results<List<ScheduleVO>> getScheduleByDoctorid(@PathVariable String doctorid){
        return Results.ok(scheduleService.getSchedulesByDoctorID(doctorid));
    }
}
