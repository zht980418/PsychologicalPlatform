package pers.qianyucc.qblog.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import pers.qianyucc.qblog.service.retrofit.*;
import retrofit2.*;
import retrofit2.converter.gson.*;

/**
 * @author mizzle rain
 * @date 2020-10-31 23:09
 */
@Configuration
public class RetrofitConfig {
    @Value("${remote.logUrl}")
    private String logUrl;

    @Bean("logService")
    public LogService logService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(logUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LogService logService = retrofit.create(LogService.class);
        return logService;
    }
}
