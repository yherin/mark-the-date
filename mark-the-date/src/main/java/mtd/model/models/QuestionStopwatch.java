package mtd.model.models;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class QuestionStopwatch extends JLabel {

    private Timer countdown;
    private final int startTime = 11;
    private int currentTime = startTime;
    private final int endTime = 0;

    public QuestionStopwatch() {
        countdown = instantiateNewTimer();
        setText(String.valueOf(currentTime));
        setFont(new Font("sansserif", 0, 28));
    }

    private Timer instantiateNewTimer() {
        int interval = 1000; //ms
        return new Timer(interval, instantiateNewActionListener());
    }

    private ActionListener instantiateNewActionListener() {
        return (ActionEvent ae) -> {
            currentTime--;
            setText("Time remaining: " + String.valueOf(currentTime));
            if (currentTime <= 0) {
                final Timer timer = (Timer) ae.getSource();
                timer.stop();
            }
        };
    }

    public void start() {
        countdown.start();
    }

    public int currentTime() {
        return this.currentTime;
    }

    public void reset() {
        currentTime = startTime;
        this.currentTime = this.startTime;
    }

    public void stop() {
        countdown.stop();
    }

}
