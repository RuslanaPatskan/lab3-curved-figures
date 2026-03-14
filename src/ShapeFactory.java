import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Фабрика геометричних фігур і їх властивостей.
 * Параметр shape_type кодує фігуру та властивості:
 * <ul>
 *   <li>десятки (shape_type / 10): тип фігури (1=зірка-3, 3=зірка-5,
 *       5=прямокутник, 6=шестикутник, 7=трикутник, 9=дуга)</li>
 *   <li>одиниці (shape_type % 10): властивість межі/заливки
 *       (1=stroke 3px, 4=stroke 7px, 7=градієнт, 8=червоний)</li>
 * </ul>
 */

public class ShapeFactory {
    /** Геометрична форма фігури. */
    public Shape       shape;
    /** Стиль обведення (товщина лінії). */
    public BasicStroke stroke;
    /** Заливка фігури (колір або градієнт). */
    public Paint       paint;
    /** Ширина фігури у пікселях. */
    public int         width;
    /** Висота фігури у пікселях. */
    public int         height;

    /**
     * Створює фігуру та встановлює її властивості за кодом shape_type.
     *
     * @param shape_type код фігури та властивостей
     * @throws Error якщо переданий код не підтримується
     */

    public ShapeFactory(int shape_type) {
        width  = 25;
        height = 25;
        stroke = new BasicStroke(3.0f);

        switch (shape_type / 10) {
            case 1:
                shape = createStar(3, new Point(0, 0), width / 2.0, width / 2.0);
                break;
            case 3:
                shape = createStar(5, new Point(0, 0), width / 2.0, width / 4.0);
                break;
            case 5:
                shape = new Rectangle2D.Double(-width / 2.0, -height / 2.0, width, height);
                break;
            case 6:
                // Hexagon
                GeneralPath hex = new GeneralPath();
                for (int i = 0; i < 6; i++) {
                    double a = Math.toRadians(60 * i - 30);
                    double px = width / 2.0 * Math.cos(a);
                    double py = height / 2.0 * Math.sin(a);
                    if (i == 0) hex.moveTo(px, py);
                    else        hex.lineTo(px, py);
                }
                hex.closePath();
                shape = hex;
                break;
            case 7:
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0) / 2.0 * height;
                path.moveTo(-width / 2.0, tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo(width / 2.0, tmp_height);
                path.closePath();
                shape = path;
                break;
            case 9:
                shape = new Arc2D.Double(-width / 2.0, -height / 2.0,
                                         width, height,
                                         30.0, 300.0, Arc2D.OPEN);
                break;
            default:
                throw new Error("type is nusupported");
        }

        switch (shape_type % 10) {
            case 1:
                stroke = new BasicStroke(3.0f);
                break;
            case 3:
                break;
            case 4:
                stroke = new BasicStroke(7.0f);
                break;
            case 7:
                paint = new GradientPaint(
                        -width,  -height, Color.white,
                         width,   height, Color.gray,
                        true);
                break;
            case 8:
                paint = Color.red;
                break;
            default:
                throw new Error("type is nusupported");
        }
    }

/**
     * Створює зіркоподібну фігуру із заданою кількістю променів.
     * Вершини чергуються між зовнішнім (rOuter) та внутрішнім (rInner) радіусом.
     *
     * @param arms   кількість променів зірки
     * @param center центральна точка фігури
     * @param rOuter радіус зовнішніх вершин
     * @param rInner радіус внутрішніх вершин
     * @return об'єкт Shape із зіркою
     */
    
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = Math.PI / arms;
        GeneralPath path = new GeneralPath();
        for (int i = 0; i < 2 * arms; i++) {
            double r = (i % 2 == 0) ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double(
                    center.x + i * angle * Math.cos(r),
                    center.y + i * angle * Math.sin(r));
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            } else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        path.closePath();
        return path;
    }
}
