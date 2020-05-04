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
    val description: String? = null

) {
    override fun toString(): String {
        return "Rocket(id=$id, rocketName=$rocketName, description=$description)"
    }
}
