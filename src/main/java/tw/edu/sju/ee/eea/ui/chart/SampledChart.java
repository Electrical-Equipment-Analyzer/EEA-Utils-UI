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
package tw.edu.sju.ee.eea.ui.chart;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tw.edu.sju.ee.eea.util.iepe.io.VoltageInputStream;
import tw.edu.sju.ee.eea.util.iepe.io.SampledInputStream;

/**
 *
 * @author Leo
 */
public class SampledChart extends JFreeChart {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("mm:ss");
    private Font font = new Font(Font.DIALOG, Font.BOLD, 14);
    private Color seriesColors[] = new Color[]{Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN, Color.YELLOW};

    public SampledChart(String title) {
        super(title, new XYPlot());
        super.getXYPlot().setDomainAxis(createAxisX());
        super.getXYPlot().setRangeAxis(createAxisY());
        super.getXYPlot().setOrientation(PlotOrientation.VERTICAL);
        ChartFactory.getChartTheme().apply(this);
    }

    public Color getSeriesColor(int i) {
        try {
            return seriesColors[i];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return new Color(i);
        }
    }

    private ValueAxis createAxisX() {
        DateAxis axis = new DateAxis("Time(mm:ss)");
        axis.setLabelFont(font);
//        axis.setNumberFormatOverride(FORMAT);
        axis.setDateFormatOverride(FORMAT);
        return axis;
    }

    private ValueAxis createAxisY() {
        NumberAxis axis = new NumberAxis("Magnitude(Voltage)");
        axis.setLabelFont(font);
        return axis;
    }

    public void addMarker(Marker mark) {
        super.getXYPlot().addDomainMarker(mark);
    }

    public static XYItemRenderer creatrRenderer() {
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, FORMAT, NumberFormat.getNumberInstance()));
        return renderer;
    }

    public void setSeriesColors(Color[] seriesColors) {
        this.seriesColors = seriesColors;
    }

    public void addData(int index, XYDataset dataset) {
        XYItemRenderer creatrRenderer = creatrRenderer();
        creatrRenderer.setSeriesPaint(0, getSeriesColor(index));
        addData(index, dataset, creatrRenderer);
    }

    public void addData(int index, XYDataset dataset, XYItemRenderer renderer) {
        super.getXYPlot().setDataset(index, dataset);
        super.getXYPlot().mapDatasetToRangeAxis(index, 0);
        super.getXYPlot().setRenderer(index, renderer);
    }

    public static XYSeriesCollection createSampledSeriesCollection(String name) {
        XYSeries series = new XYSeries(name);
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }

    public static XYSeriesCollection createSampledSeriesCollection(String name, InputStream in, int pos, int bps, int length) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series(name, in, pos, bps, length));
        return collection;
    }

    public static XYSeries series(String name, InputStream in, int pos, int bps, int length) {
        XYSeries series = new XYSeries(name);
        double tmp = 0;
        try {
            VoltageInputStream vi = new VoltageInputStream(in);
            int bpms = bps / 1000;
            vi.skip(pos * bpms);
            int end = pos + length;
            double spms = length * 0.001;
            spms = (spms < 1 ? 1.0 / bpms : (int) spms);

            int ff = (int) (bpms * spms);
            SampledInputStream ss = new SampledInputStream(vi, ff);
            for (double i = pos; i < end && ss.available() > ff; i += spms) {
                series.add(i, ss.readSampled());
            }
        } catch (java.io.EOFException ex) {
        } catch (IOException ex) {
            Logger.getLogger(SampledChart.class.getName()).log(Level.INFO, null, ex);
        }
        return series;
    }
}
