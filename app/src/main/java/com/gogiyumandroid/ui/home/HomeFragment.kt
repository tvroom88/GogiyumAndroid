@file:Suppress("DEPRECATION")

package com.gogiyumandroid.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.gogiyumandroid.R
import com.gogiyumandroid.RestaurantListActivity
import kotlinx.android.synthetic.main.custom_table_row.view.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val myGogiyumAddress = "https://gogiyum.com/api/menu"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val tableLayout = root.findViewById<TableLayout>(R.id.TableLayout1)

        readFirstData(myGogiyumAddress, tableLayout)

        return root
    }

//    synchronous
    private fun readFirstData(url: String, tableLayout: TableLayout) {
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val jsonArray = JSONArray(body)

                activity?.runOnUiThread {
                    handleTableRow(jsonArray, tableLayout)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fail")
            }
        })
    }

    fun handleTableRow(jsonArray: JSONArray, tableLayout: TableLayout){
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
                val tableRows = TableRow(activity)
                layoutInflater.inflate(R.layout.custom_table_row, tableRows, true)
                tableRows.foodImageKName.text = jsonObject.getString("name")
                tableRows.foodImageEName.text = " (" + jsonObject.getString("e_name") + ")"

                val imageUrl = jsonObject.getString("image")
                val ivImage : ImageView = tableRows.imageView1
                Glide.with(this).load(imageUrl).into(ivImage)
                tableLayout.addView(tableRows)

                tableRows.setOnClickListener(){
                    val intent = Intent(activity, RestaurantListActivity::class.java)
                    startActivity(intent)
                }
        }
    }

//    private fun readRestaurantData(url: String) {
//        val request = Request.Builder().url(url).build()
//        val client = OkHttpClient()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body()?.string()
//                val jsonArray = JSONArray(body)
//                val myRestaurantList: ArrayList<String> = ArrayList<String>()
//
//                runOnUiThread {
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        myRestaurantList.add(jsonObject.getString("address"))
//                    }
//                }
//            }
//            override fun onFailure(call: Call, e: IOException) {
//                println("fail")
//            }
//        })
//
//    }

}

