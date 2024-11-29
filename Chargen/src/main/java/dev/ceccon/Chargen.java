package dev.ceccon;

import dev.ceccon.gui.MainView;

import javax.swing.*;

public class Chargen {
    public static void main( String[] args ) {
        printBanner();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView();
            }
        });
    }

    private static void printBanner() {
        System.out.println("==================================");
        System.out.println("======  Welcome to Chargen  ======");
        System.out.println("==================================");
    }

}
