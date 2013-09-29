/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing;

import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.ni.daqmx.config.NIAnalogConfig;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.UNINITIALIZED_VALUE;
import static javax.swing.JOptionPane.getRootFrame;

/**
 *
 * @author Leo
 */
public class DAQOptionPane extends JComponent {

    protected Object value;
    private JDialog dialog;

    public static void main(String[] args) {
        DAQOptionPane.showAnalogConfigPane(null);
        System.exit(0);
    }

    public static NIAnalogConfig showAnalogConfigPane(Component parentComponent) {
        DAQOptionPane pane = new AnalogConfigPane();
        pane.dialog = pane.createDialog(parentComponent, "New Source");
        pane.dialog.show();
        return (NIAnalogConfig) pane.getValue();
    }

    public Object getValue() {
        return value;
    }

    private JDialog createDialog(Component parentComponent, String title)
            throws HeadlessException {
        final JDialog dialog;
        if (parentComponent instanceof Frame) {
            dialog = new JDialog((Frame) parentComponent, title, true);
        } else {
            dialog = new JDialog((Dialog) parentComponent, title, true);
        }

        initDialog(dialog, parentComponent);
        return dialog;
    }

    private void initDialog(final JDialog dialog, Component parentComponent) {
//        dialog.setComponentOrientation(this.getComponentOrientation());
        Container contentPane = dialog.getContentPane();
//
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);
        dialog.setResizable(false);
//        if (JDialog.isDefaultLookAndFeelDecorated()) {
//            boolean supportsWindowDecorations =
//              UIManager.getLookAndFeel().getSupportsWindowDecorations();
//            if (supportsWindowDecorations) {
//                dialog.setUndecorated(true);
//                getRootPane().setWindowDecorationStyle(style);
//            }
//        }
        dialog.setSize(this.getSize());
        dialog.setLocationRelativeTo(parentComponent);

    }

    protected JDialog getDialog() {
        return dialog;
    }

    public static Object showAnalogConfigDialog(Component parentComponent,
            Object message, String title, int messageType, Icon icon,
            Object[] selectionValues, Object initialSelectionValue)
            throws HeadlessException {
        JOptionPane pane = new JOptionPane(message, messageType,
                OK_CANCEL_OPTION, icon,
                null, null);

        pane.setWantsInput(true);
        pane.setSelectionValues(selectionValues);
        pane.setInitialSelectionValue(initialSelectionValue);
        pane.setComponentOrientation(((parentComponent == null)
                ? getRootFrame() : parentComponent).getComponentOrientation());

        JDialog dialog = pane.createDialog(parentComponent, title);

        pane.selectInitialValue();
        dialog.show();
        dialog.dispose();

        Object value = pane.getInputValue();

        if (value == UNINITIALIZED_VALUE) {
            return null;
        }
        return value;
    }
}
