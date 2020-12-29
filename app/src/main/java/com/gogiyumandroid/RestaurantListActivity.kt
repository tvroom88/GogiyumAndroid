package com.gogiyumandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.TableLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gogiyumandroid.RestaurantAdapter.RestaurantAdapter
import kotlinx.android.synthetic.main.custom_restaurant_table_row.*
import kotlinx.android.synthetic.main.custom_restaurant_table_row.view.*
import kotlinx.android.synthetic.main.custom_table_row.view.*
import okhttp3.*
import org.json.JSONArray
import java.net.URL

class RestaurantListActivity : AppCompatActivity() {
    private var task: RestaurantReadTask? = null

    lateinit var resAdapter : RestaurantAdapter
    val resArray = ArrayList<RestaurantAdapter>()
    //    private val myGogiyumRestaurant = "https://gogiyum.com/api/restaurant"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

    }


    @SuppressLint("StaticFieldLeak")
    inner class RestaurantReadTask : AsyncTask<Void, JSONArray, String>() {
        // 데이터를 읽기 전에 기존 데이터 초기화
        override fun onPreExecute() {
        }

        override fun doInBackground(vararg params: Void?): String {

            val jsonArray = readData()
//            val layout1 = findViewById<LinearLayout>(R.id.restLayout)


            runOnUiThread {
//                txt1.text = jsonArray.toString()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val n = jsonObject.getJSONArray("foods")

                    val restaurantName = jsonObject.getString("name")
                    val restaurantAddress = jsonObject.getString("address")
                    val yelpRating = jsonObject.getDouble("y_rating")
                    val googleRating = jsonObject.getDouble("g_rating")

                    val uberURL = jsonObject.getString("uber")
                    val grubURL = jsonObject.getString("grubhub")
                    val doorURL = jsonObject.getString("doordash")

                    resAdapter = RestaurantAdapter(
                        restaurantName, restaurantAddress, yelpRating, googleRating, uberURL, grubURL, doorURL
                    )

                    resAdapter.weekday_text = jsonObject.get("weekday_text").toString()
                    resAdapter.price =  jsonObject.getString("price")
                    resAdapter.menuURL =  jsonObject.getString("menu")
//                    resAdapter.phone = jsonObject.get("phone")
                    if (jsonObject.has("phone")) {
                        resAdapter.phone = jsonObject.getString("phone")
                    }else{
                        resAdapter.phone = ""
                    }

                    resArray.add(resAdapter)
                }

                jsonHandler(resArray)
            }

            return "success"
        }

        // 데이터를 읽어올때마다 중간중간 실행
        override fun onProgressUpdate(vararg values: JSONArray?) {
        }

    }

    // 화장실 정보를 읽어와 JSONObject 로 반환하는 함수
    fun readData(): JSONArray {
        val url = URL("https://gogiyum.com/api/restaurant")
        val connection = url.openConnection()
        val data = connection.getInputStream().readBytes().toString(charset("UTF-8"))
        return JSONArray(data)
    }

    fun jsonHandler(resArray: ArrayList<RestaurantAdapter>){
        val resTableLayout = findViewById<TableLayout>(R.id.resTable)


        for (i in 0 until resArray.size) {
            val tableRows = TableRow(this)
            layoutInflater.inflate(R.layout.custom_restaurant_table_row, tableRows, true)

            tableRows.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )


            tableRows.resName.text = resArray.get(i).getName()
            tableRows.resAddress.text = resArray.get(i).getAddress()
            tableRows.ratingBar1.rating = resArray.get(i).getYelpRating().toFloat()
            tableRows.ratingBar2.rating = resArray.get(i).getGoogleRating().toFloat()

            tableRows.right_part.setOnClickListener{
                val intent = Intent(this, GoogleMap::class.java)
                startActivity(intent)
            }

            tableRows.btn1.setOnClickListener(btn1Listener(i))

            tableRows.menuBtn.setOnClickListener{
                goToUrl(resArray.get(i).menuURL)
            }

            if(resArray.get(i).phone == ""){

            }else{
                tableRows.btn4.setOnClickListener{
                    phoneCall(resArray.get(i).phone)
                }
            }


            val uberURL = resArray.get(i).getUberURL()
            if(uberURL == ""){
                tableRows.uberButton.getBackground().setAlpha(60);
            }else{
                tableRows.uberButton.setOnClickListener {
                    goToUrl(resArray.get(i).getUberURL())
                }
            }

            val grupURL = resArray.get(i).getGrupURL()
            if(grupURL == "") {
                    tableRows.GrubButton.getBackground().setAlpha(60);
            } else {
                tableRows.GrubButton.setOnClickListener {
                    goToUrl(resArray.get(i).getGrupURL())
                }
            }

            val doorURL = resArray.get(i).getGrupURL()
            if(doorURL == "") {
                tableRows.DoorButton.getBackground().setAlpha(60);
            } else {
                tableRows .DoorButton.setOnClickListener {
                    goToUrl(resArray.get(i).getDoorURL())
                }
            }

            resTableLayout.addView(tableRows)
            resTableLayout.setStretchAllColumns(true);
        }

    }


    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }




    override fun onStart() {
        super.onStart()
        task?.cancel(true)
        task = RestaurantReadTask()
        task?.execute()
    }

    // 앱이 비활성화 될때 백그라운드 작업 취소
    override fun onStop() {
        super.onStop()
        task?.cancel(true)
        task = null
    }

    private fun btn1Listener(idx: Int): View.OnClickListener {
        val handleButtonClick = object : View.OnClickListener {
            override fun onClick(view: View) {
                val builder = AlertDialog.Builder(this@RestaurantListActivity)
                builder.setTitle("Androidly Alert")
                builder.setMessage(resArray.get(idx).weekday_text)

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT
                    ).show()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT
                    ).show()
                }

                builder.setNeutralButton("Maybe") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Maybe", Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            }
        }
        return handleButtonClick
    }

    private fun phoneCall(phoneNum: String){
        val TELEPHONE_SCHEMA = "tel:"

        // Step 1: Define the phone call uri
        val phoneCallUri = Uri.parse(TELEPHONE_SCHEMA + phoneNum)

        // Step 2: Set Intent action to `ACTION_DIAL`
        val phoneCallIntent = Intent(Intent.ACTION_DIAL).also{
            // Step 3. Set phone call uri to Intent data
            it.setData(phoneCallUri)
        }

        // Step 4: Pass the Intent to System to start any <Activity> which can accept `ACTION_DIAL`
        startActivity(phoneCallIntent)
    }
}

