import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Головне вікно програми "Криві фігури".
 * Створює та відображає панель з анімованими фігурами.
 *
 * <p>Варіант 5: шестикутник із межею товщиною 7 пікселів.
 */
public class TitlesFrame extends JFrame {

    public TitlesFrame() {
        initUI();
    }

    /**
     * Ініціалізує компоненти вікна: заголовок, розмір,
     * панель з фігурами та розташування на екрані.
     * Використовує enum для явного задання фігури та стилю.
     */
    private void initUI() {
        setTitle("Кривые фигуры");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new TitlesPanel(ShapeType.HEXAGON, ShapeStyle.STROKE_7PX));
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
