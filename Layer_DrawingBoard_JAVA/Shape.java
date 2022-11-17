package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Shape {
    public Point point_start;
    public Point point_end;

    public int line_thickness;
    public Color line_color;
    public Color fill_color;

    Shape(Point p_s, Point p_e){
        this.point_start = p_s;
        this.point_end = p_e;
    }

    public abstract void draw(Graphics g);
}
