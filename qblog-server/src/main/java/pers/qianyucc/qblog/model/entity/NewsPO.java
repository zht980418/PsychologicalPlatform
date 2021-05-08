package pers.qianyucc.qblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("News")
public class NewsPO implements Serializable {

    private static final long serialVersionUID = 4618994265362816529L;
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    private String cover;
    private String title;
    private String content;
    private String author;
    private String tabloid;
    private Integer views;
    private Long gmtCreate;
    private Long gmtUpdate;
}
