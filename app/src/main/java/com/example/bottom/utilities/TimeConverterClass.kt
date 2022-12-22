package com.example.bottom.utilities

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeConverterClass {
    companion object {

        private fun stringDateToCalendar(dateString: String?, formatString: String): Calendar? {
            if (dateString == null || dateString.isEmpty() || formatString.isBlank())
                return null

            val inputDateFormat = SimpleDateFormat(formatString, Locale.ENGLISH)

            return try {
                inputDateFormat.parse(dateString)?.let {
                    val cal = Calendar.getInstance()
                    cal.time = it
                    cal
                }
            } catch (e: ParseException) {
                null
            }

        }

        private fun diffBetweenNowAndCalender(time: String?): Long {
            val commitTime = stringDateToCalendar(time, "yyyy-MM-dd'T'hh:mm:ssX")

            val today = getToday()
            val msDiff = today?.timeInMillis?.minus(commitTime?.timeInMillis ?: 0) ?: 0
            return TimeUnit.MILLISECONDS.toDays(msDiff)
        }

        private fun getToday(): Calendar? {
            val df: DateFormat = SimpleDateFormat("KK:mm:ss a, dd/MM/yyyy", Locale.getDefault())
            val currentDateAndTime = df.format(Date())
            return stringDateToCalendar(currentDateAndTime, "KK:mm:ss a, dd/MM/yyyy")
        }

        fun convertDateToText(time: String?): String {
            val diffInDays = diffBetweenNowAndCalender(time).toInt()
            return when {
                diffInDays == 0 -> {
                    return getHourAndTime(time)
                }
                diffInDays == 1 -> {
                    return "Yesterday"
                }
                diffInDays <= 7 -> return dayOfWeek(time)
                else -> simpleDateOFMonth(time)
            }

        }

        private fun getHourAndTime(time: String?): String {
            val commitTime = stringDateToCalendar(time, "yyyy-MM-dd'T'hh:mm:ssX")
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val dateTime = simpleDateFormat.format(commitTime?.time).toString()
            return dateTime
        }

        private fun dayOfWeek(time: String?): String {
            val commitTime = stringDateToCalendar(time, "yyyy-MM-dd'T'hh:mm:ssX")
            val simpleDateFormat = SimpleDateFormat("EEEE")
            return simpleDateFormat.format(commitTime?.time).toString()
        }

        private fun simpleDateOFMonth(time: String?): String {
            val commitTime = stringDateToCalendar(time, "yyyy-MM-dd'T'hh:mm:ssX")
            val simpleDateFormat = SimpleDateFormat("dd/MM/yy")
            return simpleDateFormat.format(commitTime?.time).toString()
        }

    }
}