package Layer_DrawingBoard_JAVA;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Line extends Shape {

    Line(Point point_start, Point point_end) {
        super(point_start, point_end);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(super.line_thickness));
        
        g.setColor(super.line_color);
        g.drawLine(super.point_start.x, super.point_start.y, super.point_end.x, super.point_end.y);   
    }
    
}
