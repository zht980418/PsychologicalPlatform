package pers.qianyucc.qblog.exception;

import pers.qianyucc.qblog.model.comm.*;
import pers.qianyucc.qblog.model.enums.*;

/**
 * @author lijing
 * @e-mail 1413979079@qq.com
 * @date 2020-06-28 23:14
 * @description 博客中的自定义异常
 */
public class BlogException extends RuntimeException {
    private final IErrorInfo errorInfo;

    public BlogException(IErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 将异常转换为 ResultVO 对象返回给前端
     *
     * @return 封装了异常信息的 ResultVO 对象
     */
    public Results toResultVO() {
        return Results.fromErrorInfo(errorInfo);
    }
}
