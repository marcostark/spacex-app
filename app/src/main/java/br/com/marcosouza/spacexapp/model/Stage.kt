package br.com.marcosouza.spacexapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Stage(

    @Expose
    @SerializedName("core")
    val cores: List<Core>? = null,

    @Expose
    @SerializedName("block")
    val block: Int? = null
){

    override fun toString(): String {
        return "Stage(cores=$cores, block=$block)"
    }
}
