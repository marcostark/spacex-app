package br.com.marcosouza.spacexapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchConfigModel(
    @Expose
    @SerializedName("payload_id")
    var id: String?,

    @Expose
    @SerializedName( "rocket_name")
    var name: String?,

    @Expose
    @SerializedName("rocket_type")
    var type: String?,

    @Expose
    @SerializedName("first_stage")
    var firstStage: CoresListModel?,

    @Expose
    @SerializedName("second_stage")
    var secondStage: SecondStagePayloadListModel?
) : Parcelable

@Parcelize
data class CoresListModel(
    @Expose
    @SerializedName("cores") var cores: List<CoreSpecModel>?
) : Parcelable

@Parcelize
data class CoreSpecModel(
    @Expose
    @SerializedName( "core_serial") var serial: String?,
    @Expose
    @SerializedName("flight") var flight: Int?,
    @Expose
    @SerializedName("block") var block: Int?,
    @Expose
    @SerializedName("gridfins") var gridfins: Boolean?,
    @Expose
    @SerializedName("legs") var legs: Boolean?,
    @Expose
    @SerializedName("reused") var reused: Boolean?,
    @Expose
    @SerializedName("land_success") var landingSuccess: Boolean?,
    @Expose
    @SerializedName("landing_intent") var landingIntent: Boolean?,
    @Expose
    @SerializedName("landing_type") var landingType: String?,
    @Expose
    @SerializedName("landing_vehicle") var landingVehicle: String?
) : Parcelable

@Parcelize
data class SecondStagePayloadListModel(
    @Expose
    @SerializedName("block") var block: Int?,
    @Expose
    @SerializedName("payloads") var payloads: List<PayloadModel>?
) : Parcelable

@Parcelize
data class PayloadModel(
    @Expose
    @SerializedName( "payload_id") var id: String?,
    @Expose
    @SerializedName("norad_id") var noradId: List<Int>?,
    @Expose
    @SerializedName("reused") var reused: Boolean?,
    @Expose
    @SerializedName("customers") var customers: List<String>?,
    @Expose
    @SerializedName("nationality") var nationality: String?,
    @Expose
    @SerializedName("manufacturer") var manufacturer: String?,
    @Expose
    @SerializedName("payload_type") var type: String?,
    @Expose
    @SerializedName( "payload_mass_kg") var massKg: Float?,
    @Expose
    @SerializedName( "payload_mass_lbs") var massLbs: Float?,
    @Expose
    @SerializedName("orbit") var orbit: String?
) : Parcelable
