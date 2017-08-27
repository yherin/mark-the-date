package mtd.model.models;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class QuestionStopwatch extends JLabel {

    private Timer countdown;
    private final int startTime = 10;
    private int currentTime = startTime;
    private final int endTime = 0;

    public QuestionStopwatch() {
        countdown = instantiateNewTimer();
        setText(String.valueOf(currentTime));
        setFont(new Font("sansserif", 0, 28));
    }

    private Timer instantiateNewTimer() {
        int interval = 1000; //1000ms = 1s
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

    /**
     * Starts the countdown timer.
     */
    public void start() {
        countdown.start();
    }

    /**
     * Get the current time.
     *
     * @return the current time stored by the timer.
     */
    public int currentTime() {
        return this.currentTime;
    }

    /**
     * Reset the timer.
     */
    public void reset() {
        currentTime = startTime;
        this.currentTime = this.startTime;
    }

    /**
     * Stop the timer.
     */
    public void stop() {
        countdown.stop();
    }

}
