package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("Dejue")
public class DejuePO implements Serializable {

    private static final long serialVersionUID = -883018073481295914L;
    @TableId(value = "id", type = IdType.INPUT)
    private int id;
    private String cover;
    private String title;
    private String tabloid;
    private String author;
    private String link;

}
