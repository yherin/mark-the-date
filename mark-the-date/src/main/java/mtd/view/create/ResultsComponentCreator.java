/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view.create;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import mtd.view.GUIComponent;
import mtd.view.GUIComponentMap;

/**
 *
 * @author sjack
 */
public class ResultsComponentCreator extends ComponentCreator {

    private JPanel resultsPage;
    private JButton playAgain;
    private JButton quit;
    private JTextArea summary;

    public ResultsComponentCreator() {
    }

    @Override
    void createComponents() {
        createScoreSummary();
        createQuitButton();
        createPlayAgainButton();
    }

    @Override
    void mapComponents() {
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_QUIT, quit);
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_PLAY_AGAIN, playAgain);
        GUIComponentMap.setComponentToEnum(GUIComponent.LABEL_SUMMARY_TEXT, summary);
    }

    private void createScoreSummary() {
        String scoreString = "Your score: ";
        summary = new JTextArea(scoreString);
        summary.setEditable(false);
    }

    private void createPlayAgainButton() {
        playAgain = new JButton("Play again");
    }

    private void createQuitButton() {
        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

}
