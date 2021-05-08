package pers.qianyucc.qblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.NewsPO;
import pers.qianyucc.qblog.utils.DateTimeUtils;

@Data

public class NewsVO {
    private int id;
    private String cover;
    private String title;
    private String content;
    private String author;
    private String tabloid;
    private Integer views;
    private String gmtCreate;
    private String gmtUpdate;

    public static NewsVO fromNewsPO(NewsPO newsPO){return new NewsVO.Converter().convertToVO(newsPO);}
    private static class Converter implements IConverter<NewsPO, NewsVO>{
        @Override
        public NewsVO convertToVO(NewsPO newsPO){
            final NewsVO newsVO = new NewsVO();
            BeanUtil.copyProperties(newsPO, newsVO, CopyOptions.create().ignoreNullValue().ignoreError());
            newsVO.setGmtCreate(DateTimeUtils.formatDatetime(newsPO.getGmtCreate()));
            newsVO.setGmtUpdate(DateTimeUtils.formatDatetime(newsPO.getGmtUpdate()));
            return newsVO;
        }
    }
}
