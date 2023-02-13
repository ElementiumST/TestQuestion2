package io.stark.testquestion.data.datasource

import io.stark.testquestion.data.models.PersonBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("/testAndroidData")
    fun getTestAndroidData(): Call<PersonBody>
}