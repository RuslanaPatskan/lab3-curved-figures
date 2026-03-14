import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

/**
 * Перерахування стилів відображення фігур.
 * Замінює магічні числа (одиниці параметра shape_type).
 * Кожен стиль містить готові об'єкти stroke та paint.
 *
 * <p>Приклад використання:
 * <pre>
 *   ShapeFactory sf = new ShapeFactory(ShapeType.HEXAGON, ShapeStyle.STROKE_7PX);
 * </pre>
 */
public enum ShapeStyle {

    /** Обведення товщиною 3px, чорний колір (код 1). */
    STROKE_3PX(1),

    /** Стандартне обведення 3px (код 3). */
    DEFAULT(3),

    /** Обведення товщиною 7px (код 4). */
    STROKE_7PX(4),

    /** Градієнтна заливка біло-сіра (код 7). */
    GRADIENT(7),

    /** Червоний колір (код 8). */
    RED(8);

    /** Числовий код стилю (одиниці у старому форматі). */
    public final int code;

    ShapeStyle(int code) {
        this.code = code;
    }

    /**
     * Повертає ShapeStyle за числовим кодом (одиниці shape_type).
     *
     * @param code числовий код (1, 3, 4, 7, 8)
     * @return відповідний ShapeStyle
     * @throws IllegalArgumentException якщо код не підтримується
     */
    public static ShapeStyle fromCode(int code) {
        for (ShapeStyle s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unsupported style code: " + code);
    }

    /**
     * Повертає об'єкт BasicStroke для даного стилю.
     *
     * @return stroke відповідної товщини
     */
    public BasicStroke getStroke() {
        switch (this) {
            case STROKE_7PX: return new BasicStroke(7.0f);
            default:         return new BasicStroke(3.0f);
        }
    }

    /**
     * Повертає об'єкт Paint (колір або градієнт) для даного стилю.
     * Параметри size використовуються для побудови градієнту.
     *
     * @param size розмір фігури у пікселях
     * @return об'єкт Paint, ніколи не повертає null
     */
    public Paint getPaint(int size) {
        switch (this) {
            case GRADIENT:
                return new GradientPaint(-size, -size, Color.white,
                                          size,  size, Color.gray, true);
            case RED:
                return Color.red;
            default:
                return Color.black;
        }
    }
}
