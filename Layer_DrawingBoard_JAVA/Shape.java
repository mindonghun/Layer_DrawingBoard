package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.awt.Graphics;

public abstract class Shape {
    public Point point_start;
    public Point point_end;

    Shape(Point p_s, Point p_e){
        this.point_start = p_s;
        this.point_end = p_e;
    }

    public abstract void draw(Graphics g);
}
