package br.com.marcosouza.spacexapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Launch (
    @Expose
    @SerializedName("flight_number")
    val flightNumber: Int? = null,

    @Expose
    @SerializedName("mission_name")
    val missionName: String? = null,

    @Expose
    @SerializedName("launch_date_utc")
    val launchDate: Date? = null,

    @Expose
    @SerializedName("launch_date_unix")
    val launchDateUnix: Long? = null,

    @Expose
    @SerializedName("rocket")
    val rocket: LaunchConfigModel? = null,

    @Expose
    @SerializedName("launch_site")
    val launchSite: LaunchSite? = null,

    @Expose
    @SerializedName("launch_success")
    val launchSuccess: Boolean? = false,

    @Expose
    @SerializedName("details")
    val details: String? = null,

    @Expose
    @SerializedName("links")
    val links: Links? = null
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
    val siteId: String? = null,

    @Expose
    @SerializedName("site_name")
    val siteName: String? = null,

    @Expose
    @SerializedName("site_name_long")
    val siteNameLong: String? = null
){

}

class Links (

    @Expose
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String? = null,

    @Expose
    @SerializedName("reddit_launch")
    val redditLaunch: String? = null,

    @Expose
    @SerializedName("video_link")
    val videoLink: String? = null,

    @Expose
    @SerializedName("flickr_images")
    val flickrImages: List<String>? = null
){

}
