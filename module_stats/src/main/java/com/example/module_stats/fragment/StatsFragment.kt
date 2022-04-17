package com.example.module_stats.fragment

import android.graphics.Color
import androidx.appcompat.widget.AppCompatTextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.database.DBManager
import com.example.library_base.contract.IBaseContract
import com.example.library_base.view.BaseFragment
import com.example.library_common.constant.Constants
import com.example.library_common.router.RouterFragmentPath
import com.example.module_stats.R
import com.example.module_stats.bean.StatsBean
import com.example.module_stats.presenter.StatsPresenter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import java.util.*

@Route(path = RouterFragmentPath.STATS)
class StatsFragment : BaseFragment<StatsPresenter>() {
    override val mPresenter: IBaseContract.IPresenter = StatsPresenter(this)
    private val lineChart: LineChart by lazy {
        rootView.findViewById(R.id.stats_recent_line)
    }

    private val barChart: BarChart by lazy {
        rootView.findViewById(R.id.stats_recent_bar)
    }

    private val todayNumber: AppCompatTextView by lazy {
        rootView.findViewById(R.id.stats_tv_today_number)
    }

    private val weekNumber: AppCompatTextView by lazy {
        rootView.findViewById(R.id.stats_tv_week_number)
    }

    private val monthNumber: AppCompatTextView by lazy {
        rootView.findViewById(R.id.stats_tv_month_number)
    }

    private var chartColor: Int = Color.BLUE

    override fun init() {
        initPresenter()
        initChart()
    }

    private fun initChart() {
        val list =
            DBManager.instance!!.getDaoSession(requireContext()).appInfoDao.queryBuilder().list()
        if (list.size != 0) {
            when (list[0].chartColor) {
                Constants.Sp.DEFAULT_STRING -> {
                    chartColor = Color.BLUE
                }
                Constants.ChartColor.DEFAULT -> {
                    chartColor = Color.BLUE
                }
                Constants.ChartColor.RED -> {
                    chartColor = Color.RED
                }
                Constants.ChartColor.YELLOW -> {
                    chartColor = Color.YELLOW
                }
            }
        }
    }

    private fun initPresenter() {
        mPresenter.requestData()
    }

    private fun setNumber(data: StatsBean) {
        todayNumber.text = data.todayCompletedNumber.toString()
        todayNumber.contentDescription = "今日完成任务${todayNumber.text}次"
        weekNumber.text = data.weekCompletedNumber.toString()
        weekNumber.contentDescription = "本周完成任务${weekNumber.text}次"
        monthNumber.text = data.monthCompletedNumber.toString()
        monthNumber.contentDescription = "本月完成任务${monthNumber.text}次"
    }

    private fun setChart(data: StatsBean) {
        setLineChart(data)
        setBarChart(data)
    }


    private fun getChartContent(data: Array<Int>, days: Int): String {
        val buffer = StringBuffer()
        var diff = 0
        data.forEach {
            buffer.append("本月${days - diff}日，完成任务${it}次")
            diff++
        }
        return buffer.toString()
    }

    private fun setLineChart(data: StatsBean) {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val lineDataList = ArrayList<Entry>()
        var diff = 0F
        data.recentCompletedNumber.forEach {
            lineDataList.add(Entry(day - diff, it.toFloat()))
            diff++
        }
        lineDataList.reverse()
        val lineDataSet = LineDataSet(lineDataList, "最近七天完成任务数")
        lineDataSet.highlightLineWidth = 0F
        lineDataSet.setCircleColors(chartColor)
        lineDataSet.color = chartColor
        lineChart.data = LineData(lineDataSet)
        lineChart.contentDescription = getChartContent(data.recentCompletedNumber, day)
    }


    private fun setBarChart(data: StatsBean) {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val barDataList = ArrayList<BarEntry>()
        var diff = 0F
        data.recentCompletedNumber.forEach {
            barDataList.add(BarEntry(day - diff, it.toFloat()))
            diff++
        }
        barDataList.reverse()
        val barDataSet = BarDataSet(barDataList, "最近七天完成任务数")
        barDataSet.color = chartColor
        barChart.data = BarData(barDataSet)
        barChart.contentDescription = getChartContent(data.recentCompletedNumber, day)
    }

    override fun getLayoutId(): Int = R.layout.stats_fragment_layout

    override fun <T> showContent(data: T) {
        setNumber(data as StatsBean)
        setChart(data as StatsBean)
    }

}