import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Insets;

**
 * Панель відображення анімованих фігур.
 * Використовує javax.swing.Timer для покадрової анімації:
 * на кожному кадрі фігури обертаються та перемальовуються.
 */

public class TitlesPanel extends JPanel implements ActionListener {

    private Graphics2D g2d;
    private Timer animation;
    private boolean is_done;
    private int start_angle;
    private int shape;

/**
     * Створює панель з вказаним типом фігури та запускає анімацію.
     *
     * @param _shape код фігури та властивостей (tens=фігура, units=властивість)
     */
    
    public TitlesPanel(int _shape) {
        start_angle = 0;
        is_done = true;
        shape = _shape;
        animation = new Timer(50, this);
        animation.setInitialDelay(50);
        animation.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (is_done) {
            repaint();
        }
    }

/**
     * Малює сітку фігур на панелі з урахуванням поточного кута обертання.
     * Використовує AffineTransform для повороту кожної фігури навколо
     * власного центру. Фігури розміщуються рядками та стовпцями.
     *
     * @param g графічний контекст для малювання
     */
    
    private void doDrawing(Graphics g) {
        is_done = false;
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension size = getSize();
        Insets insets = getInsets();
        int w = size.width  - insets.left - insets.right;
        int h = size.height - insets.top  - insets.bottom;

        ShapeFactory sf = new ShapeFactory(shape);
        g2d.setStroke(sf.stroke);
        g2d.setPaint(sf.paint);

        double dr = start_angle;
        start_angle += 1;
        if (start_angle > 360) {
            start_angle = 0;
        }

        double step = 90.0 / ((double) w / (sf.width * 1.5));

        for (int j = sf.height; j < h; j = (int)(j + sf.height * 1.5)) {
            for (int i = sf.width; i < w; i = (int)(i + sf.width * 1.5)) {
                double angle = dr;
                if (angle > 360.0) {
                    angle = 0;
                } else {
                    angle = dr + step;
                }
                dr = angle;

                AffineTransform transform = new AffineTransform();
                transform.translate((double) i, (double) j);
                transform.rotate(Math.toRadians(angle));
                g2d.draw(transform.createTransformedShape(sf.shape));
            }
        }

        is_done = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
