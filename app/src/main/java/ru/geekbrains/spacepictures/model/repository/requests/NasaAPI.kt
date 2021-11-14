package ru.geekbrains.spacepictures.model.repository.requests

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.spacepictures.model.repository.MRP.MRPServerResponseData
import ru.geekbrains.spacepictures.model.repository.POD.PODServerResponseData

interface NasaAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String):
            Call<PODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getPictureOfTheDay(
        @Query("earth_date") date: String,
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String
    )
            : Call<MRPServerResponseData>
}
