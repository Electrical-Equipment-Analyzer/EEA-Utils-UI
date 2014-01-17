/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui;

/**
 *
 * @author Leo
 */
public abstract class Manager {

    public static Manager MANAGER;
//    public EventPool EVENT_POOL = new EventPool();
    protected MainFrame mainFrame;

    protected Manager() {
//        initEvent();
        Manager.MANAGER = this;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
        
    }

//    private void initEvent() {
//
//        //Action==============
//        EVENT_POOL.put(new EventFile());
//    }
}
