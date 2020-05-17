package br.com.marcosouza.spacexapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Core(
    @Expose
    @SerializedName("core_serial")
    val coreSerial: String? = null
){
    override fun toString(): String {
        return "Core(coreSerial=$coreSerial)"
    }
}
