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

    Shape(Point point_start, Point point_end){
        this.point_start = point_start;
        this.point_end = point_end;
        this.line_color = Tool.getTool().line_color;
        this.line_thickness = Tool.getTool().line_thickness;
        this.fill_color = Tool.getTool().fill_color;
    }

    public abstract void draw(Graphics g);
}
