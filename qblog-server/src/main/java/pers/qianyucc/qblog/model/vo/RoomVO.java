package pers.qianyucc.qblog.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomVO {

    private String roomid;
    private String name;
    private String address;
}
