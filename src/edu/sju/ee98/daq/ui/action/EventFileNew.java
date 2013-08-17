/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import edu.sju.ee98.daq.ui.MainFrame;
import edu.sju.ee98.daq.ui.fft.NewDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Leo
 */
public class EventFileNew implements Event, ActionListener {

    
    @Override
    public String name() {
        return "file_new";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("new");
                new NewDialog(MainFrame.THIS);
//        NewJDialog newJDialog = new NewJDialog(MainFrame.THIS, true);
//        newJDialog.setVisible(true);
        
        //        JTextField firstName = new JTextField();
        //        JTextField lastName = new JTextField();
        //        JPasswordField password = new JPasswordField();
        //        final JComponent[] inputs = new JComponent[]{
        //            new JLabel("First"),
        //            firstName,
        //            new JLabel("Last"),
        //            lastName,
        //            new JLabel("Password"),
        //            password
        //        };
        //        JOptionPane.showMessageDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
        //        System.out.println("You entered "
        //                + firstName.getText() + ", "
        //                + lastName.getText() + ", "
        //                + password.getText());

    }
}
