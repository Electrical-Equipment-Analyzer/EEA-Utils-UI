/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui.components;

import edu.sju.ee.eea.math.WaveGenerator;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Leo
 */
public class JFree {

    public static void main(String[] args) {
        int frequency = 1000;
        int generateLength = 1000;
        double voltage = 2;
        WaveGenerator analogGenerator = new WaveGenerator(frequency * 20, generateLength, voltage, frequency);
        WaveGenerator analogGenerator2 = new WaveGenerator(frequency * 20, generateLength, voltage, 100);
// create a dataset...
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.2);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);
        XYSeries series1 = new XYSeries("Average Weight");
        for (int i = 0; i < generateLength; i++) {
            series1.add(i, analogGenerator.getData()[i]);
        }
        
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        
        
        XYSeries series2 = new XYSeries("Average Weight");
        for (int i = 0; i < generateLength; i++) {
            series2.add(i, analogGenerator2.getData()[i]);
        }
        
        XYSeriesCollection result2 = new XYSeriesCollection();
        result2.addSeries(series2);

// create a chart...
        JFreeChart chart = ChartFactory.createXYLineChart("title", "Frequency", "H", collection);
        XYPlot plot = chart.getXYPlot();
        
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
//        axis2.setFixedDimension(10.0);
//        axis2.setAutoRangeIncludesZero(false);
        axis2.setLabelPaint(Color.red);
        axis2.setTickLabelPaint(Color.red);
        plot.setRangeAxis(1, axis2);
//        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT);

        
        plot.setDataset(1, result2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setSeriesPaint(0, Color.red);
        plot.setRenderer(1, renderer2);
// create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
