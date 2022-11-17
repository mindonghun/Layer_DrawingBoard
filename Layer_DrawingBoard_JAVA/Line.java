package Layer_DrawingBoard_JAVA;

import java.awt.Graphics;
import java.awt.Point;

public class Line extends Shape {

    Line(Point p_s, Point p_e) {
        super(p_s, p_e);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(super.point_start.x, super.point_start.y, super.point_end.x, super.point_end.y);
    }
    
}
