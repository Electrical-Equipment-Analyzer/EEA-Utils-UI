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
import org.openide.util.Exceptions;
import tw.edu.sju.ee.eea.util.iepe.io.IepeInputStream;

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

    private void creatrRenderer(int index) {
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, FORMAT, NumberFormat.getNumberInstance()));
        renderer.setSeriesPaint(0, getSeriesColor(index));
        super.getXYPlot().setRenderer(index, renderer);
    }

    public void setSeriesColors(Color[] seriesColors) {
        this.seriesColors = seriesColors;
    }

//    public void addData(int index, String label, XYDataset dataset) {
//        addData(index, index, dataset);
//    }
    public void addData(int index, XYDataset dataset) {
        super.getXYPlot().setDataset(index, dataset);
        super.getXYPlot().mapDatasetToRangeAxis(index, 0);
        creatrRenderer(index);
    }

    public static XYSeriesCollection createSampledSeriesCollection(String name, InputStream in, int pos, int bps, int length) {
        XYSeries series = new XYSeries(name);
        double tmp = 0;
        try {
            IepeInputStream vi = new IepeInputStream(in);
            int bpms = bps / 1000;
            vi.skip(pos * bpms);
            int end = pos + length;
            int spms = length / 1000;
            spms = (spms < 1 ? 1 : spms);
            int spms2 = 2 * spms;
            for (double i = pos; i < end; i++) {
                for (int j = 0; j < bpms; j++) {
                    tmp = Math.max(tmp, vi.readValue());
                }
                if (i % spms == 0) {
                    series.add(i, (i % spms2 == 0 ? -tmp : tmp));
                    tmp = 0;
                }
            }
        } catch (java.io.EOFException ex) {
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }
}
