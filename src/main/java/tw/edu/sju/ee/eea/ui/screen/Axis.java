/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.sju.ee.eea.ui.screen;

import tw.edu.sju.ee.eea.ui.Manager;
import tw.edu.sju.ee.eea.ui.swing.SpinnerMetricModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class Axis extends JPanel implements ChangeListener {

    private JSpinner postion;
    private JSpinner div;

    public Axis(String title) {
        super(null);
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setSize(400, 50);
        JLabel labelPostion = new JLabel("postion".toUpperCase());
        labelPostion.setBounds(20, 15, 60, 25);
        this.add(labelPostion);
        this.postion = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        this.postion.addChangeListener(this);
        this.postion.setBounds(100, 15, 80, 25);
        this.add(this.postion);
        JLabel labelDiv = new JLabel("div".toUpperCase());
        labelDiv.setBounds(220, 15, 60, 25);
        this.add(labelDiv);
        this.div = new JSpinner(new SpinnerMetricModel(1, 0.000000000001, 1000000));
        this.div.addChangeListener(this);
        this.div.setBounds(300, 15, 80, 25);
        this.add(this.div);
    }

    public int getPostion() {
        return (Integer) postion.getValue();
    }

    public double getDiv() {
        return (Double) div.getModel().getValue();
    }

    @Override
    public String toString() {
        return "PostionDiv{" + "postion=" + postion + ", div=" + div + '}';
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Manager.MANAGER.getMainFrame().work.getSelectedComponent().repaint();
    }
}
