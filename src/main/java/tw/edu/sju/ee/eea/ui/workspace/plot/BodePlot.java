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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tw.edu.sju.ee.eea.core.math.MetricPrefixFormat;

/**
 *
 * @author Leo
 */
public class BodePlot extends JFreeChart {

    private static final DecimalFormat FORMAT = new MetricPrefixFormat("0.###");
    private Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    private Color color1 = Color.RED;
    private Color color2 = Color.BLUE;

    public BodePlot(String title) {
        super(title, new XYPlot());
        super.getXYPlot().setDomainAxis(createAxisX());
        super.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
        ChartFactory.getChartTheme().apply(this);
    }

    private ValueAxis createAxisX() {
        LogAxis axis = new LogAxis("Frequency");
        axis.setLabelFont(font);
        axis.setNumberFormatOverride(FORMAT);
        return axis;
    }

    private NumberAxis createAxisY(String label) {
        NumberAxis axis = new NumberAxis(label);
        axis.setLabelFont(font);
        return axis;
    }

    public static final int SHOW_DATA_BOTH = 0;
    public static final int SHOW_DATA_MAGNITUDE = 1;
    public static final int SHOW_DATA_PHASE = 2;

    public void setData(Map<Double, Complex> gain, int showData) {
        int i = 0;
        if (showData != SHOW_DATA_PHASE) {
            addData(i++, "Magnitude(dB)", color1, createXYSeriesCollection("Magnitude", gain, true));
        }
        if (showData != SHOW_DATA_MAGNITUDE) {
            addData(i++, "Phase(Degrees)", color2, createXYSeriesCollection("Phase", gain, false));
        }
    }

    private void addData(int index, String label, Color color, XYDataset dataset) {
        NumberAxis axis = createAxisY(label);
        axis.setLabelPaint(color);
        axis.setTickLabelPaint(color);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, FORMAT, NumberFormat.getNumberInstance()));
        renderer.setSeriesPaint(0, color);
        super.getXYPlot().setRangeAxis(index, axis);
        super.getXYPlot().setDataset(index, dataset);
        super.getXYPlot().mapDatasetToRangeAxis(index, index);
        super.getXYPlot().setRenderer(index, renderer);
    }

    private static XYSeriesCollection createXYSeriesCollection(String name, Map<Double, Complex> gain, boolean abs) {
        XYSeries series = new XYSeries(name);
        Iterator it = gain.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Double, Complex> entry = (Map.Entry<Double, Complex>) it.next();
            series.add((double) entry.getKey(),
                    abs ? 20 * Math.log(entry.getValue().abs()) : Math.toDegrees(entry.getValue().getArgument()));
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }
}
