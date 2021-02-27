package pers.qianyucc.qblog.model.dto;

import cn.hutool.core.bean.*;
import io.swagger.annotations.*;
import lombok.*;
import pers.qianyucc.qblog.model.entity.*;

import javax.validation.constraints.*;

@Data
@ApiModel(value = "表格类", description = "前端传来的表格信息")
public class RoomDTO {
    @NotEmpty(message = "咨询室id不能为空")
    @ApiModelProperty(notes = "roomid", example = "1号")
    private String roomid;

    @ApiModelProperty(notes = "name", example = "张三")
    private String name;

    @ApiModelProperty(notes = "address", example = "电子科技大学")
    private String address;



    public RoomPO toRoomPO() {
        return new RoomDTO.Converter().convertToPO(this);
    }

    private static class Converter implements IConverter<RoomDTO, RoomPO> {
        @Override
        public RoomPO convertToPO(RoomDTO formDTO) {
            RoomPO po = new RoomPO();
            BeanUtil.copyProperties(formDTO, po);
            return po;
        }
    }
}
