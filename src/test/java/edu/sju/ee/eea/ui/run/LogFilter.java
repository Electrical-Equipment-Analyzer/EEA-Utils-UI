/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui.run;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 *
 * @author Leo
 */
public class LogFilter implements Filter {

    private static final String PREFIX = "edu.sju.ee98.daq";

    @Override
    public boolean isLoggable(LogRecord record) {
        return record.getSourceClassName().startsWith(PREFIX);
    }
}
