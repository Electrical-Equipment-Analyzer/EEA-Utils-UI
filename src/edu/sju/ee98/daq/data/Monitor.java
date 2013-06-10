/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.data;

/**
 *
 * @author Leo
 */
public interface Monitor {

    public void setChannel(int index, Channel channel);
    
    public void repaint();
}
