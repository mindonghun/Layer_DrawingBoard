package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;

public class Shape_Factory {
    public static Shape makeShape(Point point_start,Point point_end, Set<Point> point_drag_list){
        
        switch(Tool.getTool().mode_shape){
            case Line:
                return new Line(point_start,point_end);
            case Rect:
                return new Rect(point_start,point_end);
            case Oval:
                return new Oval(point_start,point_end);
            case Pen:
            case Pencil:
            case Brush:
            case Eraser:
                return new Pen(point_start,point_end,point_drag_list);
            default:
                break;
        }
        return null;
    }
}
