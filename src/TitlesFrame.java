import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TitlesFrame extends JFrame {

    public TitlesFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Кривые фигуры");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new TitlesPanel(64));
        setSize(350, 350);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitlesFrame frame = new TitlesFrame();
                frame.setVisible(true);
            }
        });
    }
}
