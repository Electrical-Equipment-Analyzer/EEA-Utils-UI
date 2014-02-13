/*
 * Copyright (C) 2014 Leo
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package tw.edu.sju.ee.eea.ui.workspace.plot;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tw.edu.sju.ee.eea.core.math.MetricPrefixFormat;

/**
 *
 * @author Leo
 */
public class BodePlotChart extends ChartPanel {

    public BodePlotChart(Map<Double, Complex> gain) {
        super(initChart(gain));
        this.setPreferredSize(new java.awt.Dimension(600, 270));
        this.setDomainZoomable(true);
        this.setRangeZoomable(true);
    }

    private static JFreeChart initChart(Map<Double, Complex> gain) {

        Font font = new Font(Font.DIALOG, Font.BOLD, 14);
        Color color1 = Color.RED;
        Color color2 = Color.BLUE;

        JFreeChart chart = ChartFactory.createXYLineChart("Bode Plot", null, "Magnitude(dB)", createXYSeriesCollection("Magnitude", gain, true), PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = chart.getXYPlot();

        LogAxis domainAxis = new LogAxis("Frequency");
        domainAxis.setLabelFont(font);
        domainAxis.setNumberFormatOverride(new MetricPrefixFormat("0.###"));
        plot.setDomainAxis(domainAxis);

        ValueAxis axis1 = plot.getRangeAxis();
        axis1.setLabelFont(font);
        axis1.setLabelPaint(color1);
        axis1.setTickLabelPaint(color1);

        NumberAxis axis2 = new NumberAxis("Phase(Degrees)");
        axis2.setLabelFont(font);
        axis2.setLabelPaint(color2);
        axis2.setTickLabelPaint(color2);
        plot.setRangeAxis(1, axis2);

        plot.setDataset(1, createXYSeriesCollection("Phase", gain, false));
        plot.mapDatasetToRangeAxis(1, 1);
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setSeriesPaint(0, color2);
        plot.setRenderer(1, renderer2);

        return chart;
    }

    private static XYSeriesCollection createXYSeriesCollection(String name, Map<Double, Complex> gain, boolean abs) {
        XYSeries series1 = new XYSeries(name);
        Iterator it = gain.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Double, Complex> entry = (Map.Entry<Double, Complex>) it.next();
            series1.add((double) entry.getKey(),
                    abs ? 20 * Math.log(entry.getValue().abs()) : Math.toDegrees(entry.getValue().getArgument()));
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        return collection;
    }
}
