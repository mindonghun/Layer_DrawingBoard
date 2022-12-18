package Layer_DrawingBoard_JAVA;

import java.awt.Point;
import java.util.Set;


public class PositionchangeCommand_Builder {
    
    static PositionchangeCommand tmp_position = null;
    static Point cur_point=null;
    // mouse_status 0:click , 1:drag , 2:released
    public static PositionchangeCommand makePositionchangeCommand(Point point_start,Point point_end, Set<Point> point_drag_list, int mouse_status){
        if(mouse_status == 1){
            if(tmp_position==null){
                System.out.println("mouse_status == 1 --- tmp_position==null");
                return new PositionchangeCommand(point_start, point_end);
            }
            else{
                if(cur_point==null){
                    System.out.println("mouse_status == 1 --- cur_point==null");
                    if(tmp_position.point_select_position(point_start)){
                        tmp_position.move_position(point_start, point_end);
                        cur_point = point_end;
                        return null;
                    }else{
                        System.out.println("mouse_status == 1 --- cur_point==null --- !point_select_position");
                        if(tmp_position.status == 0){
                            LayerManager.getLayerManager().getCurLayer().delete();
                        }
                        else{
                            tmp_position.status = 2;
                        }
                        tmp_position = null;
                        cur_point = null;
                        return null;
                    }
                }else{
                    System.out.println("mouse_status == 1 --- cur_point!=null");
                    tmp_position.move_position(cur_point, point_end);
                    cur_point = point_end;
                    return null;
                }

            }
        }else if(mouse_status==2){
            if(tmp_position == null){
                tmp_position = new PositionchangeCommand(point_start, point_end);
                return tmp_position;
            }
            else{
                if(tmp_position.point_select_position(cur_point)){
                    //tmp_position.move_position(point_start, point_end);
                    cur_point = null;
                    return null;
                }else{
                    if(tmp_position.status == 0){
                        LayerManager.getLayerManager().getCurLayer().delete();
                        tmp_position = new PositionchangeCommand(point_start, point_end);
                    }
                    else{
                        tmp_position.status = 2;
                    }
                    cur_point = null;
                    return tmp_position;
                }
            }
        }
        return null;
    }

    public static void finish_makePositionchangeCommand(){
        if(tmp_position != null){
            if(tmp_position.status == 0){
                LayerManager.getLayerManager().getCurLayer().delete();
            }
            else{
                tmp_position.status = 2;
            }
            tmp_position = null;
        }
    }

}
