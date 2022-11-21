package Layer_DrawingBoard_JAVA;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

public class Pen extends Shape {

    Set<Point> point_drag_list;

    Pen(Point point_start, Point point_end, Set<Point> point_drag_list) {
        super(point_start, point_end);
        super.line_color = Tool.getTool().line_color;
        super.line_thickness = Tool.getTool().line_thickness;
        this.point_drag_list = point_drag_list;

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(super.line_color);

        int x,y,w,h;
        x = point_start.x - (int)(super.line_thickness/2);
        y = point_start.y - (int)(super.line_thickness/2);
        w = h = super.line_thickness;
        g.fillRect(x, y, w, h);

        for(Point p : point_drag_list){
            x = p.x - (int)(super.line_thickness/2);
            y = p.y - (int)(super.line_thickness/2);
            g.fillRect(x, y, w, h);
        }

        x = point_end.x - (int)(super.line_thickness/2);
        y = point_end.y - (int)(super.line_thickness/2);
        g.fillRect(x, y, w, h);
        

    }
    
}
