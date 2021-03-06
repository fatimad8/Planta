package com.example.planta.network

import com.example.planta.model.Item
import com.example.planta.model.Liked
import com.example.planta.model.Order
import com.example.planta.model.WishList
import retrofit2.Call
import retrofit2.http.*

interface WishListService {

    @POST("uesrs/{id}/wishlist")
    fun wishList(@Path("id")id:String, @Body wishList: WishList): Call<WishList>

    @POST("uesrs/{id}/wishlist/{wid}/Liked")
    fun addLikedItem(@Path("id")uid:String,@Path("wid")wId:String,@Body like: Liked):Call<Liked>

    @GET("uesrs/{id}/wishlist/{wid}/Liked")
    fun getUserWishlist(@Path("id")uid:String,@Path("wid")wId:String):Call<List<Liked>>

    @GET("uesrs/{id}/wishlist")
    fun getUserWishByUid(@Path("id") uid:String,@Query("uesrId") uesrId:String):Call<List<WishList>>

    @DELETE("uesrs/{id}/wishlist/{wid}/Liked/{lid}")
    fun removeFromWishlist(@Path("id")uid:String,@Path("wid")wId:String,@Path("lid") lid:String):Call<Liked>

    @GET("uesrs/{id}/wishlist/{wid}/Liked")
    fun getLidByName(@Path("id")uid:String,@Path("wid")wId:String,@Query("name") name:String):Call<List<Liked>>
}