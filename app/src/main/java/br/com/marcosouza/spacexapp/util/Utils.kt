package br.com.marcosouza.spacexapp.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {

        @JvmStatic
        fun toSimpleString(date: Date?) : String {
            val format = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
            return format.format(date)
        }
}
