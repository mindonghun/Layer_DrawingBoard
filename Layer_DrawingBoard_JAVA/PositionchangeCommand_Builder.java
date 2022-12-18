package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;


public class PositionchangeCommand_Builder {
    
    static PositionchangeCommand cur_position = null;
    static Point cur_point=null;
    // mouse_status 0:click , 1:drag , 2:released
    public static PositionchangeCommand makePositionchangeCommand(Point point_start,Point point_end, Set<Point> point_drag_list, int mouse_status){
        if(mouse_status == 1){
            if(cur_position==null){
                System.out.println("mouse_status == 1 --- cur_position==null");
                return new PositionchangeCommand(point_start, point_end);
            }
            else{
                if(cur_point==null){
                    System.out.println("mouse_status == 1 --- cur_point==null");
                    if(cur_position.point_select_position(point_start)){
                        cur_position.move_position(point_start, point_end);
                        cur_point = point_end;
                        return null;
                    }else{
                        System.out.println("mouse_status == 1 --- cur_point==null --- !point_select_position");
                        if(cur_position.status == 0){
                            LayerManager.getLayerManager().getCurLayer().delete();
                        }
                        else{
                            cur_position.status = 2;
                        }
                        cur_position = null;
                        cur_point = null;
                        return null;
                    }
                }else{
                    System.out.println("mouse_status == 1 --- cur_point!=null");
                    cur_position.move_position(cur_point, point_end);
                    cur_point = point_end;
                    return null;
                }

            }
        }else if(mouse_status==2){
            if(cur_position == null){
                cur_position = new PositionchangeCommand(point_start, point_end);
                return cur_position;
            }
            else{
                if(cur_position.point_select_position(cur_point)){
                    //cur_position.move_position(point_start, point_end);
                    cur_point = null;
                    return null;
                }else{
                    if(cur_position.status == 0){
                        LayerManager.getLayerManager().getCurLayer().delete();
                        cur_position = new PositionchangeCommand(point_start, point_end);
                    }
                    else{
                        cur_position.status = 2;
                    }
                    cur_point = null;
                    return cur_position;
                }
            }
        }
        return null;
    }

    public static void finish_makePositionchangeCommand(){
        if(cur_position != null){
            if(cur_position.status == 0){
                LayerManager.getLayerManager().getCurLayer().delete();
            }
            else{
                cur_position.status = 2;
            }
            cur_position = null;
        }
    }

}
