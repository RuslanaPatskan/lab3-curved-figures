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

public class ShapeFactory {

    public Shape       shape;
    public BasicStroke stroke;
    public Paint       paint;
    public int         width;
    public int         height;

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
