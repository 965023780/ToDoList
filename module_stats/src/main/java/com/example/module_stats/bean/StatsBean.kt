package com.example.module_stats.bean

data class StatsBean(val todayCompletedNumber: Int,
                     val weekCompletedNumber: Int,
                     val monthCompletedNumber: Int,
                     val totalCompletedNumber: Int,
                     val recentCompletedNumber: Array<Int>
                     ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StatsBean

        if (todayCompletedNumber != other.todayCompletedNumber) return false
        if (weekCompletedNumber != other.weekCompletedNumber) return false
        if (monthCompletedNumber != other.monthCompletedNumber) return false
        if (totalCompletedNumber != other.totalCompletedNumber) return false
        if (!recentCompletedNumber.contentEquals(other.recentCompletedNumber)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = todayCompletedNumber
        result = 31 * result + weekCompletedNumber
        result = 31 * result + monthCompletedNumber
        result = 31 * result + totalCompletedNumber
        result = 31 * result + recentCompletedNumber.contentHashCode()
        return result
    }
}
