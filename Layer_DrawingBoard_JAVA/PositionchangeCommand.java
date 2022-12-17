package Layer_DrawingBoard_JAVA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class PositionchangeCommand implements Command{

    public Point point_start;
    public Point point_end;

    public int x,y,w,h,dx,dy;

    // 0 : 아무 작업도 이루어지지 않음
    // 1 : 작업이 이루어짐
    // 2 : 작업이 이루어지고 종료됨
    public int status;

    PositionchangeCommand(Point point_start, Point point_end){
        super();
        this.point_start = point_start;
        this.point_end = point_end;
        dx = dy = 0;
        status = 0;
        if(point_end.x - point_start.x >= 0){
            x = point_start.x;
            w = point_end.x - point_start.x;
        }else{
            x = point_end.x;
            w = point_start.x - point_end.x;
        }

        if(point_end.y - point_start.y >= 0 ){
            y = point_start.y;
            h = point_end.y - point_start.y;
        }else{
            y = point_end.y;
            h = point_start.y - point_end.y;
        }
    }

    public boolean point_select_position(Point point){
        int _x = point.x - (x+dx);
        int _y = point.y - (y+dy);
        if( _x<0 || _x>w ){
            return false;
        }
        if( _y<0 || _y>h){
            return false;
        }
        status = 1;
        return true;
    }

    public void move_position(Point point_start, Point point_end){
        dx += point_end.x - point_start.x;
        dy += point_end.y - point_start.y;
        
    }

    @Override
    public void execute(Graphics g) {
        g.copyArea(x,y,w,h, dx,dy);
        g.clearRect(x, y, w, h);

        if(status != 2){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(1));
            g.setColor(Color.RED);
            g.drawRect(x+dx, y+dy, w, h);
        }

    }
    
}
