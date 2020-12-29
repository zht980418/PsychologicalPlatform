package pers.qianyucc.qblog.service.retrofit;

import pers.qianyucc.qblog.service.retrofit.model.*;
import retrofit2.*;
import retrofit2.http.*;

/**
 * @author mizzle rain
 * @date 2020-10-31 22:43
 */

public interface LogService {
    /**
     * 写入请求日志
     *
     * @param requestLogDTO 日志信息
     * @return 返回结果
     */
    @POST("/reqlog")
    Call<HttpResult> writeRequestLog(@Body RequestLogDTO requestLogDTO);
}
