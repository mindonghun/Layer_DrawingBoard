package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;

public class Command_Factory {
    public static Command makeCommand(Point p_s,Point p_e, Set<Point> p_d_list){
        
        switch(Tool.mode_command){
            case DrawCommand:
                return new DrawCommand(Shape_Factory.makeShape(p_s,p_e,p_d_list));
            case ImageProcessingCommand:

                break;
            case PositionchangeCommand:

                break;
            default:
                break;
        }
        return null;
    }
}
