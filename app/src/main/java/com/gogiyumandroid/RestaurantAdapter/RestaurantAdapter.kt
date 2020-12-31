package com.gogiyumandroid.RestaurantAdapter

class RestaurantAdapter(val _name: String, val _address: String, val _yrating: Double, val _grating: Double,
                        val _uberURL: String, val _gruhub: String, val _doordash: String) {
    val resName: String = _name //restaurantName
    val resAddress: String = _address
    val y_rating : Double = _yrating
    val g_rating : Double = _grating

    val uURL: String = _uberURL //restaurantName
    val gURL: String = _gruhub
    val dURL: String = _doordash


    var weekday_text: String = ""
        get() = if (field.length > 0) field else ""
        set (value) {
            if (value.length > 0) field = value else ""
        }

    var price: String = ""
        get() = if (field.length > 0) field else ""
        set (value) {
            if (value.length > 0) field = value else ""
        }

    var phone: String = ""
        get() = if (field.length > 0) field else ""
        set (value) {
            if (value.length > 0) field = value else ""
        }

    var menuURL: String = ""
        get() = if (field.length > 0) field else ""
        set (value) {
            if (value.length > 0) field = value else ""
        }

    var city: String = ""
        get() = if (field.length > 0) field else ""
        set (value) {
            if (value.length > 0) field = value else ""
        }


    fun getName(): String {
        return resName;
    }

    fun getAddress(): String {
        return resAddress;
    }

    fun getYelpRating(): Double {
        return y_rating;
    }

    fun getGoogleRating(): Double {
        return g_rating;
    }

    fun getUberURL(): String {
        return uURL;
    }

    fun getGrupURL(): String {
        return gURL;
    }

    fun getDoorURL(): String {
        return dURL;
    }

}



