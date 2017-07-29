/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sjack
 */
public class MyFrame extends JFrame implements Runnable {

    @Override
    public void run() {
        super.setBackground(Color.yellow);
        super.setSize(new Dimension(500, 500));
        super.setVisible(true);
        JPanel jp = createPanel();
        jp.setSize(new Dimension(20, 60));
        jp.setBackground(Color.red);
        jp.setVisible(Boolean.TRUE);
        jp.add(new JButton("4"));
        jp.add(new JButton("3"));
        jp.add(new JButton("2"));
        jp.add(new JButton("1"));

        this.add(jp);
        this.paint(super.getGraphics());
    }

    public MyFrame() {
        System.out.println("zomg constructor");
    }

    private JPanel createPanel() {
        JPanel jp = new JPanel();
        return jp;
    }

}
