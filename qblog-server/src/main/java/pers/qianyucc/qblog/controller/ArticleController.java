package pers.qianyucc.qblog.controller;

import cn.hutool.core.map.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.model.vo.*;
import pers.qianyucc.qblog.service.*;

import javax.validation.*;
import java.util.*;

@Api("与文章相关的api接口")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("批量获取文章")
    @GetMapping("/articles")
    public Results<PageVO> getArticles(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return Results.ok(articleService.getArticles(page, limit));
    }

    @PostMapping("/auth/articles")
    @ApiOperation("新增文章")
    public Results<Map<String, Object>> postArticles(@ApiParam(name = "文章信息", value = "传入json格式", required = true)
                                                     @RequestBody @Valid ArticleDTO articleDTO) {
        String id = articleService.insArticle(articleDTO);
        return Results.ok(MapUtil.of("id", id));
    }

    @GetMapping("/article/{id}")
    @ApiOperation("根据id查询文章信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results<ArticleVO> getArticle(@PathVariable String id) {
        ArticleVO articleVO = articleService.findById(id);
        return Results.ok(articleVO);
    }

    @DeleteMapping("/auth/article/{id}")
    @ApiOperation("根据id删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
        return Results.ok("删除成功", null);
    }

    @PutMapping("/auth/article/{id}")
    @ApiOperation("修改文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    public Results<Map<String, Object>> putArticle(@ApiParam(name = "要修改的文章信息", value = "传入json格式", required = true)
                                                    @RequestBody ArticleDTO articleDTO,
                                                    @PathVariable String id) {
        articleService.updateArticle(articleDTO, id);
        return Results.ok("更新成功", MapUtil.of("id", id));
    }

    @GetMapping("/timeline")
    @ApiOperation("获取文章时间线")
    public Results<List<TimelineVO>> getTimeline() {
        List<TimelineVO> timeline = articleService.timeline();
        return Results.ok(timeline);
    }
}
