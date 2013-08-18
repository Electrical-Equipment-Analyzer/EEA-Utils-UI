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
public class EventPool extends HashMap<String, Event> {

    public Event put(Event event) {
        return super.put(event.getName(), event);
    }
}
