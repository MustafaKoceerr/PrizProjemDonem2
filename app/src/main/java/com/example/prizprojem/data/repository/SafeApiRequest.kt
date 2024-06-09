package com.example.prizprojem.data.repository

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    // api'ları cagirirken yardım etmesi icin kullanacagimiz class
    suspend fun<T: Any> safeApiRequest(call: suspend () -> Response<T>) : T{
        // suspend fun getMovies() alacak
        // getMovies()
        val response = call() // call.invoke() == call()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            // @todo handle api exception
            throw ApiException(response.code().toString())
            // error kodunu döndürüyoruz
        }
    }
}



class ApiException(message: String) : IOException(message){

}