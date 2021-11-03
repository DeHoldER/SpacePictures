package ru.geekbrains.spacepictures.model.repository.MRP


sealed class MRPData {
    data class Success(val serverResponseData: MRPServerResponseData) : MRPData()
    data class Error(val error: Throwable) : MRPData()
    data class Loading(val progress: Int?) : MRPData()
}
