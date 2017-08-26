/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view.create;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import mtd.view.GUIComponent;
import mtd.view.GUIComponentMap;

/**
 *
 * @author sjack
 */
public class WelcomeComponentCreator extends ComponentCreator {

    private JTextArea welcomeText;
    private JButton startButton;

    @Override
    void createComponents() {
        createStartButton();
        createWelcomeText();
    }

    @Override
    void mapComponents() {
        GUIComponentMap.setComponentToEnum(GUIComponent.WELCOME_TEXT, welcomeText);
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_START, startButton);
    }

    private void createWelcomeText() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("Welcome to Mark The Date. Players must guess the year in which specific events occured within the first millenium.");
        welcome.append("\nA more accurate guess awards more points. Bonus points are given if you can answer quickly. There is no penalty for slow answers.");
        welcomeText = new JTextArea(20, 20);
        welcomeText.setWrapStyleWord(true);
        welcomeText.setLineWrap(true);
        welcomeText.setText(welcome.toString());
        welcomeText.setFont(new Font("sansserif", 0, 20));
    }

    private void createStartButton() {
        startButton = new JButton("Start!");

    }
}
