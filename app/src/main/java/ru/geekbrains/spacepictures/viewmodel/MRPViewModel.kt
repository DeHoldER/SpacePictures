package ru.geekbrains.spacepictures.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.geekbrains.spacepictures.BuildConfig
import ru.geekbrains.spacepictures.model.repository.MRP.MRPData
import ru.geekbrains.spacepictures.model.repository.MRP.MRPServerResponseData
import ru.geekbrains.spacepictures.model.repository.requests.RetrofitImpl
import ru.geekbrains.spacepictures.util.CAMERA_NAVCAM
import java.util.*

class MRPViewModel(

    private val liveDataForViewToObserve: MutableLiveData<MRPData> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()

) : ViewModel() {
    private var daysAgo = 0

    fun getData(): MutableLiveData<MRPData> {
        sendServerRequest(getFormattedDate(0))
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(date: String) {
        liveDataForViewToObserve.value = MRPData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            MRPData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl
                .getRetrofitImpl()
                .getPictureOfTheDay(
                    date = date,
                    apiKey = apiKey,
                    camera = CAMERA_NAVCAM
                )
                .enqueue(object : Callback<MRPServerResponseData> {
                    override fun onResponse(
                        call: Call<MRPServerResponseData>,
                        response: Response<MRPServerResponseData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            if (!response.body()!!.photosArray.isNullOrEmpty()) {
                                liveDataForViewToObserve.value =
                                    MRPData.Success(response.body()!!)
                                daysAgo = 0
                            } else {
                                daysAgo++
                                sendServerRequest(getFormattedDate(daysAgo))
                            }
                        } else {
                            val message = response.message()
                            if (message.isNullOrEmpty()) {
                                liveDataForViewToObserve.value =
                                    MRPData.Error(
                                        Throwable(
                                            "Unidentified error"
                                        )
                                    )
                            } else {
                                liveDataForViewToObserve.value =
                                    MRPData.Error(Throwable(message))
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<MRPServerResponseData>, t: Throwable
                    ) {
                        liveDataForViewToObserve.value = MRPData.Error(t)
                    }
                })
        }
    }

    private fun getFormattedDate(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -daysAgo)

        return "${
            calendar.get(Calendar.YEAR)
        }-${
            calendar.get(Calendar.MONTH) + 1
        }-${
            calendar.get(Calendar.DATE)
        }"
    }
}