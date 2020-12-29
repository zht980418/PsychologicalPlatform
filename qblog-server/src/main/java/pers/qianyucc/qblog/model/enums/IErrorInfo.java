package pers.qianyucc.qblog.model.enums;

/**
 * @author lijing
 * @e-mail 1413979079@qq.com
 * @date 2020-06-28 23:15
 * @description
 */
public interface IErrorInfo {
    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMsg();

    /**
     * 获取错误码
     * @return 错误码
     */
    int getCode();
}
