package com.daita.aegle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_health_report.*
import java.util.*


class HealthReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_report)

        val calendar: Calendar = Calendar.getInstance()
        val d1: Date = calendar.getTime()
        calendar.add(Calendar.DATE, 1)
        val d2: Date = calendar.getTime()
        calendar.add(Calendar.DATE, 1)
        val d3: Date = calendar.getTime()

        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                DataPoint(d1, 1.0),
                DataPoint(d2, 4.2),
                DataPoint(d3, 6.9)
            )
        )
        health_graph.addSeries(series)
        health_graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        health_graph.gridLabelRenderer.numHorizontalLabels = 3

        graph_bg.setOnClickListener {
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(
                arrayOf(
                    DataPoint(d1, 1.0),
                    DataPoint(d2, 4.2),
                    DataPoint(d3, 6.9)
                )
            )
            health_graph.removeAllSeries()
            health_graph.addSeries(series)
            health_graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
            health_graph.gridLabelRenderer.numHorizontalLabels = 3
        }

        graph_bp.setOnClickListener {
            val series: LineGraphSeries<DataPoint> = LineGraphSeries(
                arrayOf(
                    DataPoint(d1, 2.0),
                    DataPoint(d2, 36.2),
                    DataPoint(d3, 3.9)
                )
            )
            health_graph.removeAllSeries()
            health_graph.addSeries(series)
            health_graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
            health_graph.gridLabelRenderer.numHorizontalLabels = 3
        }

        health_graph.gridLabelRenderer.setHumanRounding(true)
    }
}