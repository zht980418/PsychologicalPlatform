package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.Scale2DTO;
import pers.qianyucc.qblog.model.vo.Scale2VO;
import pers.qianyucc.qblog.service.Scale2Service;

import javax.validation.Valid;
import java.util.List;

@Api("问卷2相关接口")
@RestController
public class Scale2Controller {
    @Autowired
    private Scale2Service scale2Service;

    @ApiOperation("新增问卷2预约数据")
    @PostMapping("/scale2Info")
    public Results<String> postScale2(@ApiParam(name = "问卷2预约信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid Scale2DTO scale2DTO) {
        scale2Service.insScale2(scale2DTO);
//        scale2Service.insScale2(scale2DTO);
        return Results.ok("表单新增成功", null);
    }

    @ApiOperation("根据id删除scale2")
    @DeleteMapping("/scale2Info/{scale2id}")
    @ApiImplicitParam(name = "scale2id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results deleteScale2(@PathVariable int scale2id) {
        scale2Service.deleteScale2(scale2id);
        return Results.ok("删除成功", null);
    }

    @ApiOperation("根据id修改表格")
    @PutMapping("/scale2Info/{scale2id}")
    @ApiImplicitParam(name = "scale2id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<String> putScale2(@ApiParam(name = "问卷2信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid Scale2DTO scale2DTO ,
                                   @PathVariable int scale2id){
        scale2Service.updateScale2(scale2DTO, scale2id);
        return Results.ok("表单修改成功", null);
    }

    @ApiOperation("获取问卷2列表")
    @GetMapping("/scale2Info")
    @ApiImplicitParam(name = "scale2id", value = "问卷2id", required = true, dataType = "String", paramType = "path")
    public Results<List<Scale2VO>> getScale2s() {
        return Results.ok(scale2Service.getAllScale2s());
    }
}
