package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;

public class Shape_Factory {
    public static Shape makeShape(Point p_s,Point p_e, Set<Point> p_d_list){
        
        switch(Tool.mode_shape){
            case Line:
                return new Line(p_s,p_e);
            case Rect:
                return new Rect(p_s,p_e);
            case Oval:

                break;
            case Pen:

                break;
            default:
                break;
        }
        return null;
    }
}
