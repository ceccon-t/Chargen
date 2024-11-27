package dev.ceccon;

import dev.ceccon.gui.MainView;

import javax.swing.*;

public class Chargen {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView();
            }
        });
    }
}
