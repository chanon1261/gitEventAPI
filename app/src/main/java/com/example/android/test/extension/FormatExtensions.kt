package com.example.android.test.extension

import com.ocpsoft.pretty.time.PrettyTime
import org.joda.time.DateTime
import java.text.NumberFormat

fun Long?.formatted() = if (this != null) NumberFormat.getInstance().format(this) else null

fun PrettyTime.format(isoString: String) = this.format(DateTime(isoString).toDate())