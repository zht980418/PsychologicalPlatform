package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.CourseDTO;
import pers.qianyucc.qblog.model.dto.ScheduleDTO;
import pers.qianyucc.qblog.model.vo.CourseVO;
import pers.qianyucc.qblog.model.vo.PageVO;
import pers.qianyucc.qblog.model.vo.UserQuizInfoVO;
import pers.qianyucc.qblog.service.CourseService;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("增添课程")
    @PostMapping("/course")
    public Results<String> postCourse(@ApiParam(name = "课程信息", value = "传入json格式", required = true)
                                      @RequestBody @Valid CourseDTO courseDTO){
        courseService.insCourse(courseDTO);
        return Results.ok("课程增加成功",null);
    }

    @ApiOperation("根据id删除课程")
    @DeleteMapping("/course/{courseid}")
    @ApiImplicitParam(name = "courseid", value = "课程id", required = true, dataType = "String", paramType = "path")
    public Results deleteCourse(@PathVariable String courseid) {
        courseService.deleteCourse(courseid);
        return Results.ok("课程删除成功", null);
    }

    @ApiOperation("根据id修改课程信息")
    @PutMapping("/course/{courseid}")
    @ApiImplicitParam(name = "courseid", value = "课程id", required = true, dataType = "String", paramType = "path")
    public Results<String> putSchedule(@ApiParam(name = "课程信息", value = "传入json格式", required = true)
                                       @RequestBody @Valid CourseDTO courseDTO,
                                       @PathVariable String courseid){
        courseService.updateCourse(courseDTO,courseid);
        return Results.ok("课程修改成功", null);
    }
    @ApiOperation("获取课程列表")
    @GetMapping("/courselist")
    public Results<PageVO<CourseVO>> getQuizByUid(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "10") Integer limit
    ){return Results.ok(courseService.getAllcourse(page,limit));}

    @ApiOperation("获取单个课程信息")
    @GetMapping("/course/{courseid}")
    @ApiImplicitParam(name = "courseid", value = "课程id", required = true, dataType = "String", paramType = "path")
    public Results<CourseVO> getCourseByID(@PathVariable String courseid) {return  Results.ok(courseService.getCourse(courseid));}
}
