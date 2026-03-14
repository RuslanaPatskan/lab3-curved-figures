import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Головне вікно програми "Криві фігури".
 * Створює та відображає панель з анімованими фігурами.
 */

public class TitlesFrame extends JFrame {

    public TitlesFrame() {
        initUI();
    }

/**
     * Ініціалізує компоненти вікна: заголовок, розмір,
     * панель з фігурами та розташування на екрані.
     */
    
    private void initUI() {
        setTitle("Кривые фигуры");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new TitlesPanel(64));
        setSize(350, 350);
        setLocationRelativeTo(null);
    }

/**
     * Точка входу програми. Запускає графічний інтерфейс
     * у потоці подій Swing через SwingUtilities.invokeLater.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitlesFrame frame = new TitlesFrame();
                frame.setVisible(true);
            }
        });
    }
}
