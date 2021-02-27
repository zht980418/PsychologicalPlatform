package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@TableName("Room")
public class RoomPO implements Serializable{

    private static final long serialVersionUID = -4365781215697530905L;
//    @TableId(value = "roomid")
    @TableId(value = "roomid", type = IdType.INPUT)
    private String roomid;
    private String name;
    private String address;

}
