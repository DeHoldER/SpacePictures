package ru.geekbrains.spacepictures.model.repository.MRP

import com.google.gson.annotations.SerializedName

data class MRPServerResponseData(
    @field:SerializedName("photos") val photosArray: Array<MRPServerResponseDetails>?,
)

data class MRPServerResponseDetails(
    @field:SerializedName("earth_date") val date: String?,
    @field:SerializedName("img_src") val imageSource: String?,
)
