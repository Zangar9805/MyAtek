package kz.qazapp.myatek.fragments.shedulespack;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String baseUrl="http://f0224829.xsph.ru/";

    @GET("api")
    Call<List<Model>> getModels(@Query("group_name") String gName, @Query("kurs") int gKurs, @Query("group_id") int gId);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> createUser(
            @Field("user_name") String userName,
            @Field("user_last_name") String userLastName,
            @Field("user_group_name") String userGroupName
            );
}
