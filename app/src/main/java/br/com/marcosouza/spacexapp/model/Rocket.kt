package br.com.marcosouza.spacexapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rocket (
    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("rocket_name")
    val rocketName: String? = null,

    @Expose
    @SerializedName("description")
    val description: String? = null,

    @Expose
    @SerializedName("rocket_type")
    val rocketType: String? = null,

    @Expose
    @SerializedName("active")
    val active: Boolean? = false,

    @Expose
    @SerializedName("stages")
    val stages: Int? = null,

    @Expose
    @SerializedName("cost_per_launch")
    val costPerLaunch: String? = null,

    @Expose
    @SerializedName("first_flight")
    val firstFlight: String? = null,

    @Expose
    @SerializedName("company")
    val company: String? = null,

    @Expose
    @SerializedName("country")
    val country: String? = null

) {
    override fun toString(): String {
        return "Rocket(id=$id, rocketName=$rocketName, description=$description, rocketType=$rocketType)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rocket

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        return result
    }


}
