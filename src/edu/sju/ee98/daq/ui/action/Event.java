/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @param <String>
 * @param <ActionListener>
 * @author Leo
 */
public abstract class Event extends HashMap<String, ActionListener> {

    public abstract String getName();
}
