package app.v.verbundstudium.com.verbundstudiumapp.mensa

import com.google.gson.annotations.SerializedName

data class Gericht(@SerializedName("name") val name: String,
                   @SerializedName("description") val description: String,
                   @SerializedName("priceStudent") val priceStudent: Double,
                   @SerializedName("priceGuest") val priceGuest: Double)