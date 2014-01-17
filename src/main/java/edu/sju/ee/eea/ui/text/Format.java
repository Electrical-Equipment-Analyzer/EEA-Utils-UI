/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui.text;

/**
 *
 * @author Leo
 */
public class Format {

    public static String text(String format) {
        return format.replaceAll("\\^", "");
    }

    public static char mnemonic(String format) {
        int index = format.indexOf("^", 0);
        return index < 0 ? ' ' : format.charAt(++index);
    }
}
