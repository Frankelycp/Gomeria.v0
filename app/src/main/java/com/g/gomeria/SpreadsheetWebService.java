package com.g.gomeria;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SpreadsheetWebService {

    @POST("1FAIpQLSd9jl6cAH5h8wmwFok8xh-ECLLpXWTqUopgzmNBQM5_xt3VMw/formResponse")
    @FormUrlEncoded
    Call<Void> feedbackSend(
            @Field("entry.134542249") String fuego,
            @Field("entry.1046561229") String medida,
            @Field("entry.1754148933") String tipo,
            @Field("entry.1121324986") String marca,
            @Field("entry.1569510950") String observaciones
    );

}
