/**
 * Перерахування типів геометричних фігур.
 * Замінює магічні числа (десятки параметра shape_type).
 *
 * <p>Приклад використання:
 * <pre>
 *   ShapeFactory sf = new ShapeFactory(ShapeType.HEXAGON, ShapeStyle.STROKE_7PX);
 * </pre>
 */
public enum ShapeType {

    /** Зірка з 3 кутами (код 1). */
    STAR_3(1),

    /** Зірка з 5 кутами (код 3). */
    STAR_5(3),

    /** Прямокутник (код 5). */
    RECTANGLE(5),

    /** Шестикутник (код 6). */
    HEXAGON(6),

    /** Трикутник (код 7). */
    TRIANGLE(7),

    /** Дуга (код 9). */
    ARC(9);

    /** Числовий код фігури (десятки у старому форматі). */
    public final int code;

    ShapeType(int code) {
        this.code = code;
    }

    /**
     * Повертає ShapeType за числовим кодом (десятки shape_type).
     *
     * @param code числовий код (1, 3, 5, 6, 7, 9)
     * @return відповідний ShapeType
     * @throws IllegalArgumentException якщо код не підтримується
     */
    public static ShapeType fromCode(int code) {
        for (ShapeType t : values()) {
            if (t.code == code) return t;
        }
        throw new IllegalArgumentException("Unsupported shape code: " + code);
    }
}
