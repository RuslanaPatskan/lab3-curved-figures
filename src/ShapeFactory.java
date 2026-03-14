import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Фабрика геометричних фігур і їх властивостей.
 *
 * <p>Рекомендований спосіб використання (через enum):
 * <pre>
 *   ShapeFactory sf = new ShapeFactory(ShapeType.HEXAGON, ShapeStyle.STROKE_7PX);
 * </pre>
 *
 * <p>Зворотна сумісність — старий конструктор досі працює:
 * <pre>
 *   ShapeFactory sf = new ShapeFactory(64); // 6=hexagon, 4=7px stroke
 * </pre>
 */
public class ShapeFactory {

    /** Геометрична форма фігури. */
    public final Shape       shape;
    /** Стиль обведення (товщина лінії). */
    public final BasicStroke stroke;
    /** Заливка фігури (колір або градієнт), ніколи не null. */
    public final Paint       paint;
    /** Ширина фігури у пікселях. */
    public final int         width  = 25;
    /** Висота фігури у пікселях. */
    public final int         height = 25;

    /**
     * Основний конструктор — створює фігуру за типом і стилем.
     * Використовує enum замість магічних чисел.
     *
     * @param type  тип фігури (ShapeType.HEXAGON, TRIANGLE тощо)
     * @param style стиль відображення (ShapeStyle.STROKE_7PX тощо)
     */
    public ShapeFactory(ShapeType type, ShapeStyle style) {
        this.shape  = buildShape(type);
        this.stroke = style.getStroke();
        this.paint  = style.getPaint(width);
    }

    /**
     * Конструктор зворотної сумісності — приймає старий числовий код.
     * Десятки кодують тип фігури, одиниці — стиль.
     *
     * @param shape_type числовий код (наприклад 64 = шестикутник + 7px)
     * @deprecated Використовуйте {@link #ShapeFactory(ShapeType, ShapeStyle)}
     */
    @Deprecated
    public ShapeFactory(int shape_type) {
        this(ShapeType.fromCode(shape_type / 10),
             ShapeStyle.fromCode(shape_type % 10));
    }

    /**
     * Будує геометричну фігуру за типом.
     *
     * @param type тип фігури
     * @return об'єкт Shape
     */
    private Shape buildShape(ShapeType type) {
        switch (type) {
            case STAR_3:
                return createStar(3, new Point(0, 0), width / 2.0, width / 2.0);
            case STAR_5:
                return createStar(5, new Point(0, 0), width / 2.0, width / 4.0);
            case RECTANGLE:
                return new Rectangle2D.Double(-width / 2.0, -height / 2.0, width, height);
            case HEXAGON: {
                GeneralPath hex = new GeneralPath();
                for (int i = 0; i < 6; i++) {
                    double a  = Math.toRadians(60 * i - 30);
                    double px = width  / 2.0 * Math.cos(a);
                    double py = height / 2.0 * Math.sin(a);
                    if (i == 0) hex.moveTo(px, py);
                    else        hex.lineTo(px, py);
                }
                hex.closePath();
                return hex;
            }
            case TRIANGLE: {
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0) / 2.0 * height;
                path.moveTo(-width / 2.0,  tmp_height);
                path.lineTo( 0.0,         -tmp_height);
                path.lineTo( width / 2.0,  tmp_height);
                path.closePath();
                return path;
            }
            case ARC:
                return new Arc2D.Double(-width / 2.0, -height / 2.0,
                                        width, height, 30.0, 300.0, Arc2D.OPEN);
            default:
                throw new IllegalArgumentException("Unsupported shape type: " + type);
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
        double      angle = Math.PI / arms;
        GeneralPath path  = new GeneralPath();
        for (int i = 0; i < 2 * arms; i++) {
            double r = (i % 2 == 0) ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double(
                    center.x + i * angle * Math.cos(r),
                    center.y + i * angle * Math.sin(r));
            if (i == 0) path.moveTo(p.getX(), p.getY());
            else        path.lineTo(p.getX(), p.getY());
        }
        path.closePath();
        return path;
    }
}
