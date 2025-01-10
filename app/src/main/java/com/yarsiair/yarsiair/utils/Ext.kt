package com.yarsiair.yarsiair.utils

import com.yarsiair.yarsiair.R
import com.yarsiair.yarsiair.models.Status
import kotlin.math.roundToInt

fun Int.toStatus(): Int {
    return when (this) {
        in Status.GOOD.min..Status.GOOD.max -> R.drawable.bg_biru
        in Status.MEDIUM.min..Status.MEDIUM.max -> R.drawable.bg_yellow
        in Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.min..Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.max -> R.drawable.bg_oren
        in Status.UNHEALTHY.min..Status.UNHEALTHY.max -> R.drawable.bg_merah
        in Status.VERY_UNHEALTHY.min..Status.VERY_UNHEALTHY.max -> R.drawable.bg_merah
        in Status.VERY_UNHEALTHY_SEVERE.min..Status.VERY_UNHEALTHY_SEVERE.max -> R.drawable.bg_merah
        else -> throw IllegalArgumentException("Value $this is out of range")
    }
}


fun Int.toStatusName(): String {
    return when (this) {
        in Status.GOOD.min..Status.GOOD.max -> Status.GOOD.value
        in Status.MEDIUM.min..Status.MEDIUM.max -> Status.MEDIUM.value
        in Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.min..Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.max -> Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.value
        in Status.UNHEALTHY.min..Status.UNHEALTHY.max -> Status.UNHEALTHY.value
        in Status.VERY_UNHEALTHY.min..Status.VERY_UNHEALTHY.max -> Status.VERY_UNHEALTHY.value
        in Status.VERY_UNHEALTHY_SEVERE.min..Status.VERY_UNHEALTHY_SEVERE.max -> Status.VERY_UNHEALTHY_SEVERE.value
        else -> throw IllegalArgumentException("Value $this is out of range")
    }
}

fun Double.formatRoundedDouble(): String {
    return this.roundToInt().toString()
}

fun Double.toStatusColor(): Int {
    return when (this.roundToInt()) {
        in Status.GOOD.min..Status.GOOD.max -> R.color.green_gradient
        in Status.MEDIUM.min..Status.MEDIUM.max -> R.color.yellow_gradient
        in Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.min..Status.UNHEALTHY_FOR_SENSITIVE_GROUPS.max -> R.color.orange_gradient
        in Status.UNHEALTHY.min..Status.UNHEALTHY.max -> R.color.red_gradient
        in Status.VERY_UNHEALTHY.min..Status.VERY_UNHEALTHY.max -> R.color.purple_gradient
        in Status.VERY_UNHEALTHY_SEVERE.min..Status.VERY_UNHEALTHY_SEVERE.max -> R.color.grey_gradient
        else -> throw IllegalArgumentException("Value $this is out of range")
    }
}
