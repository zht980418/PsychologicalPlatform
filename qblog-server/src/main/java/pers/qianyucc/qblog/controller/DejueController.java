package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.DejueDTO;
import pers.qianyucc.qblog.model.vo.DejueVO;
import pers.qianyucc.qblog.service.retrofit.DejueService;

import javax.validation.Valid;
import java.util.List;

@Api("得觉相关接口")
@RestController
public class DejueController {
    @Autowired
    private DejueService dejueService;

    //    增
    @ApiOperation("新增得觉数据")
    @PostMapping("/dejue")
    public Results<String> postDejue(@ApiParam(name = "得觉信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid DejueDTO dejueDTO) {
        dejueService.insDejue(dejueDTO);
        return Results.ok("表单新增成功", null);
    }
    //    删
    @ApiOperation("根据id删除dejue")
    @DeleteMapping("/dejue/{id}")
    @ApiImplicitParam(name = "id", value = "得觉id", required = true, dataType = "int", paramType = "path")
    public Results deleteDejue(@PathVariable int id) {
        dejueService.deleteDejue(id);
        return Results.ok("删除成功", null);
    }
    //    改
    @ApiOperation("根据id修改得觉")
    @PutMapping("/dejue/{id}")
    @ApiImplicitParam(name = "id", value = "得觉id", required = true, dataType = "String", paramType = "path")
    public Results<String> putDejue(@ApiParam(name = "得觉信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid DejueDTO dejueDTO ,
                                   @PathVariable int id){
        dejueService.updateDejue(dejueDTO, id);
        return Results.ok("表单修改成功", null);
    }
    //    批量查
    @ApiOperation("获取得觉列表")
    @GetMapping("/dejue")
    @ApiImplicitParam(name = "id", value = "得觉id", required = true, dataType = "String", paramType = "path")
    public Results<List<DejueVO>> getDejue() {
        return Results.ok(dejueService.getAllDejue());
    }
    //    id查
    @ApiOperation("根据id获取得觉")
    @GetMapping("/dejue/{id}")
    @ApiImplicitParam(name="id", value = "得觉id", required = true,dataType = "int", paramType = "path")
    public Results<DejueVO> getDejueByid(@PathVariable int id){
        return Results.ok(dejueService.getDejueByid(id));
    }
}
