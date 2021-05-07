package pers.qianyucc.qblog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.Results;
import pers.qianyucc.qblog.model.dto.NewsDTO;
import pers.qianyucc.qblog.model.vo.NewsVO;
import pers.qianyucc.qblog.service.NewsService;

import javax.validation.Valid;
import java.util.List;

@Api("新闻相关接口")
@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

//    增
    @ApiOperation("新增新闻数据")
    @PostMapping("/news")
    public Results<String> postNews(@ApiParam(name = "新闻信息", value = "传入json格式", required = true)
                                    @RequestBody @Valid NewsDTO newsDTO) {
        newsService.insNews(newsDTO);
        return Results.ok("表单新增成功", null);
    }
//    删
    @ApiOperation("根据id删除news")
    @DeleteMapping("/news/{id}")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "int", paramType = "path")
    public Results deleteNews(@PathVariable int id) {
        newsService.deleteNews(id);
        return Results.ok("删除成功", null);
    }
//    改
    @ApiOperation("根据id修改新闻")
    @PutMapping("/news/{id}")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "String", paramType = "path")
    public Results<String> putNews(@ApiParam(name = "新闻信息", value = "传入json格式", required = true)
                                   @RequestBody @Valid NewsDTO newsDTO ,
                                   @PathVariable int id){
        newsService.updateNews(newsDTO, id);
        return Results.ok("表单修改成功", null);
    }
//    批量查
    @ApiOperation("获取新闻列表")
    @GetMapping("/news")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "String", paramType = "path")
    public Results<List<NewsVO>> getNews() {
        return Results.ok(newsService.getAllNews());
    }
//    id查
    @ApiOperation("根据id获取新闻")
    @GetMapping("/news/{id}")
    @ApiImplicitParam(name="id", value = "新闻id", required = true,dataType = "int", paramType = "path")
    public Results<NewsVO> getNewsByid(@PathVariable int id){
        return Results.ok(newsService.getNewsByid(id));
    }
}
