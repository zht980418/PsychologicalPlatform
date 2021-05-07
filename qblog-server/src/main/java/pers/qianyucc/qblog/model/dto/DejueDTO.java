package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.qianyucc.qblog.model.entity.DejuePO;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "每日得觉",description = "每日得觉")
public class DejueDTO {
    @ApiModelProperty(notes = "roomid", example = "1")
    private int id;
    @NotEmpty(message = "封面不能为空")
    @ApiModelProperty(notes = "cover", example = "cover.jpg")
    private String cover;
    @NotEmpty(message = "标题不能为空")
    @ApiModelProperty(notes = "title", example = "《标题》")
    private String title;
    @NotEmpty(message = "摘要不能为空")
    @ApiModelProperty(notes = "abstract", example = "abstract")
    private String tabloid;
    @NotEmpty(message = "作者不能为空")
    @ApiModelProperty(notes = "author", example = "作者")
    private String author;
    @NotEmpty(message = "链接不能为空")
    @ApiModelProperty(notes = "link", example = "链接")
    private String link;

    public DejuePO toDejuePO() {
        return new DejueDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<DejueDTO, DejuePO> {
        @Override
        public DejuePO convertToPO(DejueDTO dejueDTO) {
            DejuePO po = new DejuePO();
            BeanUtil.copyProperties(dejueDTO, po);
            return po;
        }
    }
}
