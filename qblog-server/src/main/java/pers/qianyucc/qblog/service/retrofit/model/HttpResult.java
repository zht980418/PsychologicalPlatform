package pers.qianyucc.qblog.service.retrofit.model;

import lombok.*;

import java.io.*;

/**
 * @author mizzle_rain
 * @date 2020年10月31日22:50:36
 */
@Data
public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = 4444094432688829757L;
    private int code;
    private String message;
    private T data;
    private String timestamp;
}