package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.NewsPO;
import pers.qianyucc.qblog.model.entity.NewsPO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "新闻", description = "前端的新闻信息")
public class NewsDTO {

    @ApiModelProperty(notes = "roomid", example = "1")
    private int id;
    @NotEmpty(message = "封面不能为空")
    @ApiModelProperty(notes = "cover", example = "cover.jpg")
    private String cover;
    @NotEmpty(message = "标题不能为空")
    @ApiModelProperty(notes = "title", example = "《标题》")
    private String title;
    @NotEmpty(message = "内容不能为空")
    @ApiModelProperty(notes = "content", example = "内容")
    private String content;
    @NotEmpty(message = "作者不能为空")
    @ApiModelProperty(notes = "author", example = "作者")
    private String author;
    @NotEmpty(message = "摘要不能为空")
    @ApiModelProperty(notes = "abstract", example = "abstract")
    private String tabloid;

    public NewsPO toNewsPO() {
        return new NewsDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<NewsDTO, NewsPO> {
        @Override
        public NewsPO convertToPO(NewsDTO newsDTO) {
            NewsPO po = new NewsPO();
            BeanUtil.copyProperties(newsDTO, po);
            return po;
        }
    }
}
