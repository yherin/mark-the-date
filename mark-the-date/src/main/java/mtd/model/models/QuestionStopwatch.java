package mtd.model.models;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * QuestionStopwatch provides timer functionality. It is a countdown from when
 * the question started. It updates its own JLabel by a defined interval of
 * 1000ms. It can be stopped, started and reset by public methods.
 */
public class QuestionStopwatch extends JLabel {

    /**
     * Timer object which provides the counting functionality.
     *
     * @see Timer
     */
    private Timer countdown;
    /**
     * Time from which the stopwatch counts down.
     */
    private final int startTime = 10;
    /**
     * Current time.
     */
    private int currentTime = startTime;
    /**
     * Time at which the stopwatch stops counting.
     */
    private final int endTime = 0;

    /**
     * Creates a new QuestionStopwatch, and performs setup of text and timer.
     */
    public QuestionStopwatch() {
        setText(String.valueOf(currentTime));
        setFont(new Font("sansserif", 0, 28));
    }

    private Timer instantiateNewTimer() {
        int interval = 1000; //1000ms = 1s
        return new Timer(interval, instantiateNewActionListener());
    }

    /**
     * Creates an action listener which updates this timer's JTextArea when
     * triggered.
     *
     * @return ae the created ActionListener
     */
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
        if (countdown == null) {
            countdown = instantiateNewTimer();
        }
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
