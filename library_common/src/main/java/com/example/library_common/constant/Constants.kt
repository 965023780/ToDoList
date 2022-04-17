package com.example.library_common.constant

interface Constants {
    //Sp查询失败后的值
    interface Sp {
        companion object {
            const val SHARED_PREFERENCE_FILE_NAME = "ToDo_sp"
            const val DEFAULT_LONG = -1L
            const val DEFAULT_INT = -1
            const val DEFAULT_BOOLEAN = false
            const val DEFAULT_STRING = "default"
        }
    }

    //普通模式和关怀（大字）模式
    interface Theme{
        companion object{
            const val THEME = "theme"
            const val NORMAL = "normal"
            const val CARE = "care"
        }
    }

    //统计模块的图表颜色，考虑到单色盲等
    interface ChartColor{
        companion object{
            const val CHARTCOLOR = "chartColor"
            const val DEFAULT = "blue" //蓝色盲罕见
            const val YELLOW = "yellow"
            const val RED = "red"
        }
    }


}