package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Recordform")
public class RecordformPO implements Serializable {
    private static final long serialVersionUID = -2679205547569551589L;

    @TableId(value = "consultno", type = IdType.INPUT)
    private int consultno;
    private String type;
    private String userid;
    private String doctorname;
    private String date1;
    private String date2;
    private String date3;
    private String consulttype;
    private String mainpoint;
    private String nextexist;
    private String specialattention;
    private String followplan;
    private String sign;
    private String complaint;
    private String impression;
}
