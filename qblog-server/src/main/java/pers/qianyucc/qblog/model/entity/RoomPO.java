package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Room")
public class RoomPO implements Serializable{

    private static final long serialVersionUID = -4365781215697530905L;
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
//    private String doctorid;
//    private String orderid;
//    private String uid;
//    private String type;
    private String name;
//    private String ordertime;
//    private String gender;
//    private String birth;
//    private String occupation;
//    private String phone;
    private String address;
//    private String emergency;
//    private String emergencyphone;
//    private String question;
//    private String family;
//    private String expectation;
//    private String history;
//    private String test;
//    private String sleep;
//    private String relationship;
//    private String stress;
//    private String mood;
//    private String hurt;
//    private String suicide;
    private String roomid;
//    private String start;
//    private String end;
}
