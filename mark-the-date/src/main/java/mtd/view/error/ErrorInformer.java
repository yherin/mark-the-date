package mtd.view.error;

import javax.swing.JOptionPane;

/**
 *
 * @author sjack
 */
public class ErrorInformer extends JOptionPane {

    public static void showError(Throwable tr, String msg) {
        showMessageDialog(null, tr.getClass() + "\n" + msg, tr.getClass().toGenericString(), JOptionPane.ERROR_MESSAGE);
        System.exit(0);

    }
}
