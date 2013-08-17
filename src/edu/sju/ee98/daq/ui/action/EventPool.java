/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import java.util.HashMap;

/**
 *
 * @author Leo
 */
public class EventPool<String, Event> extends HashMap<String, Event> {

    public Event put(Event value) {
        return super.put((String) value.getClass().getName(), value);
    }
}
