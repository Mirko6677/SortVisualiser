import java.awt.EventQueue;

public class Main {
    private static Dialog dialog;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                dialog = new Dialog();
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}