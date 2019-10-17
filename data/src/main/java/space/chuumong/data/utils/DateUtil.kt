package space.chuumong.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"

    fun getToday(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault()).format(date)
    }

    fun getTomorrow(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val date = calendar.time
        return SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault()).format(date)
    }
}