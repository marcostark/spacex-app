package br.com.marcosouza.spacexapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Launch (
    @Expose
    @SerializedName("flight_number")
    val flightNumber: Int? = null,

    @Expose
    @SerializedName("mission_name")
    val missionName: String? = null,

    @Expose
    @SerializedName("launch_date_utc")
    val launchDate: String? = null,

    @Expose
    @SerializedName("rocket")
    val rocket: Rocket? = null,

    @Expose
    @SerializedName("launch_site")
    val launchSite: LaunchSite? = null


){

    override fun toString(): String {
        return "Launch(flightNumber=$flightNumber, missionName=$missionName, launchDate=$launchDate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Launch

        if (flightNumber != other.flightNumber) return false

        return true
    }

    override fun hashCode(): Int {
        return flightNumber ?: 0
    }
}

class LaunchSite (
    @Expose
    @SerializedName("site_id")
    val siteId: Int? = null,

    @Expose
    @SerializedName("site_name")
    val siteName: String? = null,

    @Expose
    @SerializedName("site_name_long")
    val siteNameLong: String? = null
){

}
