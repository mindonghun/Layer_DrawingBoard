package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;

public class Command_Factory {

    // status 0 : click, 1 : drag, 2 : released
    public static Command makeCommand(Point p_s,Point p_e, Set<Point> p_d_list, int status){
        switch(Tool.getTool().mode_command){
            case DrawCommand:
                return new DrawCommand(Shape_Factory.makeShape(p_s,p_e,p_d_list));

            case ImageProcessingCommand:
                return new ImagePCommand(new ImageMat());

            case PositionchangeCommand:
                return PositionchangeCommand_Builder.makePositionchangeCommand(p_s, p_e, p_d_list, status);
            default:
                break;
        }
        return null;
    }
}
