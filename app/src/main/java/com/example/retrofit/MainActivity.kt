package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BaseUrl ="https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GetMyData()
    }

    private fun GetMyData() {
        val txt =findViewById<TextView>(R.id.txt)
        val retrofitBuilder=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BaseUrl).build().create(Apiterface::class.java)

        val retrofitData=retrofitBuilder.getdata()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responsebody=response.body()!!
                val string=StringBuilder()
                for (MyData in responsebody)
                {
                    string.append(MyData.id)
                    string.append("\n")
                }
                txt.text=string
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","Failure"+t.message)
            }
        })
    }
}