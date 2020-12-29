package pers.qianyucc.qblog.service;

import cn.hutool.core.convert.*;
import cn.hutool.core.map.*;
import cn.hutool.core.util.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import pers.qianyucc.qblog.dao.*;
import pers.qianyucc.qblog.exception.*;
import pers.qianyucc.qblog.model.dto.*;
import pers.qianyucc.qblog.model.entity.*;
import pers.qianyucc.qblog.model.vo.*;
import pers.qianyucc.qblog.utils.*;

import java.util.*;
import java.util.stream.*;

import static pers.qianyucc.qblog.model.enums.ErrorInfoEnum.*;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public PageVO<ArticleVO> getArticles(int page, int limit) {
        QueryWrapper<ArticlePO> qw = new QueryWrapper<>();
        qw.select(ArticlePO.class, i -> !"content".equals(i.getColumn()));
        Page<ArticlePO> res = articleMapper.selectPage(new Page<>(page, limit), qw);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        PageVO<ArticleVO> pageVO = PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }

    public String insArticle(ArticleDTO articleDTO) {
        ArticlePO po = articleDTO.toArticlePO(false);
        String id = IdUtil.objectId();
        po.setId(id);
        articleMapper.insert(po);
        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    public ArticleVO findById(String id) {
        ArticlePO articlePO = articleMapper.selectById(id);
        if (Objects.isNull(articlePO)) {
            throw new BlogException(INVALID_ID);
        }
        articlePO.setViews(articlePO.getViews() + 1);
        articleMapper.updateById(articlePO);
        return ArticleVO.fromArticlePO(articlePO);
    }

    public void deleteArticle(String id) {
        int i = articleMapper.deleteById(id);
        if (i <= 0) {
            throw new BlogException(INVALID_ID);
        }
    }

    public void updateArticle(ArticleDTO articleDTO, String id) {
        ArticlePO dbArticle = articleMapper.selectById(id);
        if (Objects.isNull(dbArticle)) {
            throw new BlogException(INVALID_ID);
        }
        ArticlePO articlePO = articleDTO.toArticlePO(true);
        articlePO.setId(id);
        articleMapper.updateById(articlePO);
    }

    public List<TimelineVO> timeline() {
        ArrayList<TimelineVO> res = new ArrayList<>();
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("id", "title", "gmt_create");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        maps.stream().map(m -> TimelineVO.Item.builder()
                .id((String) m.get("id"))
                .gmtCreate(DateTimeUtils.formatDate((Long) m.get("gmt_create")))
                .title((String) m.get("title"))
                .build())
                .collect(Collectors.groupingBy(item -> {
                    String[] arr = item.getGmtCreate().split("-");
                    String year = arr[0];
                    return year;
                })).forEach((k, v) -> res.add(TimelineVO.builder().year(k).items(v).build()));
        res.sort(Comparator.comparing(TimelineVO::getYear).reversed());
        // log.info("timeline list : {}", JSONUtil.toJsonStr(res));
        return res;
    }

    /**
     * 获取统计表格需要的数据
     *
     * @return
     */
    public StatisticsVO countArticlesByMonth() {
        StatisticsVO statisticsVO = new StatisticsVO();
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("gmt_create");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        Map<String, List<Long>> dateCountMap = maps.stream().map(item -> Convert.toLong(item.get("gmt_create")))
                .collect(Collectors.groupingBy(item -> DateTimeUtils.formatDatetime(item, "yyyy/MM")));
        MapUtil.sort(dateCountMap).forEach((k, v) -> statisticsVO.put(k, v.size()));
        return statisticsVO;
    }
}
