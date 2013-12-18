/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Leo
 */
public class JFree {

    public static void main(String[] args) {
// create a dataset...
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.2);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);
        XYSeries series = new XYSeries("Average Weight");
        series.add(20.0, 20.0);
        series.add(40.0, 25.0);
        series.add(55.0, 50.0);
        series.add(70.0, 65.0);
        DefaultXYDataset xyDataset = new DefaultXYDataset();
        xyDataset.addSeries("aa" , new double[][]{{0.0, 1.0}, {9.9, 9.9}});
        xyDataset.addSeries("ss" , new double[][]{{2.2, 3.3, 2.2, 3.3}, {2.2, 3.3, 2.2, 6.3}});
// create a chart...
        JFreeChart chart = ChartFactory.createXYLineChart("title", "Frequency", "H", xyDataset);
// create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
