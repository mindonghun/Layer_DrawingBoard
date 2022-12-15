package Layer_DrawingBoard_JAVA;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Test extends Shape {

    Test(Point point_start, Point point_end) {
        super(point_start, point_end);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(super.line_thickness));
        
        int x,y,w,h;
        if(super.point_end.x - super.point_start.x >= 0){
            x = super.point_start.x;
            w = super.point_end.x - super.point_start.x;
        }else{
            x = super.point_end.x;
            w = super.point_start.x - super.point_end.x;
        }

        if(super.point_end.y - super.point_start.y >= 0 ){
            y = super.point_start.y;
            h = super.point_end.y - super.point_start.y;
        }else{
            y = super.point_end.y;
            h = super.point_start.y - super.point_end.y;
        }

        // g.setColor(super.fill_color);
        // g.fillRect(x, y, w, h);
        // g.setColor(super.line_color);
        // g.drawRect(x, y, w, h);
        
        g.copyArea(x,y,w,h, 100,100);
        g.clearRect(x, y, w, h);

    }

    
}
