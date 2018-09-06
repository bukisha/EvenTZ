package com.example.bookee.eventz.data;

import com.example.bookee.eventz.create.pojos.FetchUploadDataResponse;
import com.example.bookee.eventz.data.pojos.Logo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MediaUploadWebApi {

    @GET("media/upload")
    Call<FetchUploadDataResponse> requestUpload(@Query("token") String token, @Query("type") String logoType);

    @Multipart
    @POST
    Call<Void> uploadImage(@Url String uploadUrl,
                           @Part("AWSAccessKeyId") RequestBody AWSAccessKeyId,
                           @Part("acl") RequestBody acl,
                           @Part("bucket") RequestBody bucket,
                           @Part("key") RequestBody key,
                           @Part("policy") RequestBody policy,
                           @Part("signature") RequestBody signature,
                           @Part MultipartBody.Part imageFile);
//    @FormUrlEncoded
//    @Headers("Content-Type: application/x-www-form-urlencoded")
//    @POST
//    Call<Void> uploadImage(@Url String uploadUrl,
//                           @Field("AWSAccessKeyId") String AWSAccessKeyId,
//                           @Field("acl") String acl,
//                           @Field("bucket") String bucket,
//                           @Field("key") String key,
//                           @Field("policy") String policy,
//                           @Field("signature") String signature,
//                           @Field("file") File imageFile);

    @FormUrlEncoded
    @POST("media/upload/")
    Call<Logo> uploadEndToken(@Field("upload_token") String uploadToken, @Query("token") String authToken);
}
