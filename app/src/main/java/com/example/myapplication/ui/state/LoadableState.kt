package com.example.myapplication.ui.state

import com.revolut.rxdata.core.Data

sealed class LoadableState<T>(open val data: T? = null) {
    data class Loading<T>(override val data: T? = null) : LoadableState<T>(data)
    data class Success<T>(override val data: T?) : LoadableState<T>(data)
    data class Error<T>(override val data: T? = null) : LoadableState<T>(data)


    companion object {
        fun <T> Data<T>.toLoadableState() : LoadableState<T>{
            return if(this.loading) {
                Loading()
            } else {
                if(this.error != null) {
                    Error()
                } else {
                    Success(this.content)
                }
            }
        }
    }
}
