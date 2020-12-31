package com.gogiyumandroid

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.activity_google_map.*

class GoogleMap : AppCompatActivity() {

    val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    // 퍼미션 승인 요청시 사용하는 요청 코드
    val REQUEST_PERMISSION_CODE = 1

    // 기본 맵 줌 레벨
    val DEFAULT_ZOOM_LEVEL = 17f


    val CITY_HALL = LatLng(37.5662952, 126.97794509999994)
    // 구글 맵 객체를 참조할 멤버 변수
    var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)

        // 맵뷰에 onCreate 함수 호출
        mapView.onCreate(savedInstanceState)
        // 앱이 실행될때 런타임에서 위치 서비스 관련 권한체크
//        if (hasPermissions()) {
//            // 권한이 있는 경우 맵 초기화
////            initMap()
//        } else {
//            // 권한 요청
//            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)
//        }
//        // 현재 위치 버튼 클릭 이벤트 리스너 설정
//        myLocationButton.setOnClickListener { onMyLocationButtonClick() }
//    }
//
//
//    // 앱에서 사용하는 권한이 있는지 체크하는 함수
//    fun hasPermissions(): Boolean {
//        // 퍼미션목록중 하나라도 권한이 없으면 false 반환
//        for (permission in PERMISSIONS) {
//            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                return false
//            }
//        }
//        return true
//    }
//
    // 맵 초기화하는 함수
//    @SuppressLint("MissingPermission")
//    fun initMap() {
//        // 맵뷰에서 구글 맵을 불러오는 함수. 컬백함수에서 구글 맵 객체가 전달됨
//        mapView.getMapAsync {
//            // ClusterManager 객체 초기화
//            clusterManager = ClusterManager(this, it)
//            clusterRenderer = ClusterRenderer(this, it, clusterManager)
//
//            // OnCameraIdleListener 와 OnMarkerClickListener 를 clusterManager 로 지정
//            it.setOnCameraIdleListener(clusterManager)
//            it.setOnMarkerClickListener(clusterManager)
//
//            // 구글맵 멤버 변수에 구글맵 객체 저장
//            googleMap = it
//            // 현재위치로 이동 버튼 비활성화
//            it.uiSettings.isMyLocationButtonEnabled = false
//            // 위치 사용 권한이 있는 경우
//            when {
//                hasPermissions() -> {
//                    // 현재위치 표시 활성화
//                    it.isMyLocationEnabled = true
//                    // 현재위치로 카메라 이동
//                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL))
//                }
//                else -> {
//                    // 권한이 없으면 서울시청의 위치로 이동
//                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(CITY_HALL, DEFAULT_ZOOM_LEVEL))
//                }
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    fun getMyLocation(): LatLng {
//        // 위치를 측정하는 프로바이더를 GPS 센서로 지정
//        val locationProvider: String = LocationManager.GPS_PROVIDER
//        // 위치 서비스 객체를 불러옴
//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        // 마지막으로 업데이트된 위치를 가져옴
//        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)
//        // 위도 경도 객체로 반환
//
//        if (lastKnownLocation != null) {
//            return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
//        }
//        return LatLng(0.0, 0.0)
//    }
//
//
//
//
//    private fun onMyLocationButtonClick() {
//        TODO("Not yet implemented")
//    }
    }
}




