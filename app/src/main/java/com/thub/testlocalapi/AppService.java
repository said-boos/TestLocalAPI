package com.thub.testlocalapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by SAID BOOS on 5/29/2023
 */
public interface AppService {
    @POST("common/userlogin")
    Call<CommonResponse> login(@Body CommonRequest request);
}
