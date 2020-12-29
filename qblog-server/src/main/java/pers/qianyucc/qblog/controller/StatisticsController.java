package pers.qianyucc.qblog.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.vo.*;
import pers.qianyucc.qblog.service.*;

@Api("博客统计信息")
@RestController
public class StatisticsController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取文章数统计信息（按月）")
    @GetMapping("/auth/chart/articles")
    public Results<StatisticsVO> getArticles() {
        StatisticsVO statisticsVO = articleService.countArticlesByMonth();
        return Results.ok(statisticsVO);
    }
}
