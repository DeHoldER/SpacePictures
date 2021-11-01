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
import java.text.SimpleDateFormat
import java.util.*

class MRPViewModel(

    private val liveDataForViewToObserve: MutableLiveData<MRPData> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()

) : ViewModel() {

    fun getData(): MutableLiveData<MRPData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun getDate(): String {
        val today = SimpleDateFormat("yyyy-M-dd")
        return today.format(Date())
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MRPData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            MRPData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl
                .getRetrofitImpl()
                .getPictureOfTheDay(
                    date = getDate(),
                    apiKey = apiKey,
                    camera = "navcam"
                )
                .enqueue(object : Callback<MRPServerResponseData> {
                    override fun onResponse(
                        call: Call<MRPServerResponseData>,
                        response: Response<MRPServerResponseData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataForViewToObserve.value =
                                MRPData.Success(response.body()!!)
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
}