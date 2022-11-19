package Layer_DrawingBoard_JAVA;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasPanel extends JPanel{
    Dimension size;
    Point point_start, point_end, point_drag;
    Set<Point> point_drag_list;
    LayerManager layer_manager;
    Command tmp_command = null;
    CanvasMouseListener mouse_listener;


    CanvasPanel(){
        System.out.println("CanvaslPanel");

        layer_manager = new LayerManager();
        point_drag_list = new HashSet<Point>();

        mouse_listener = new CanvasMouseListener(this);
        
        setBackground(Color.WHITE);
        addMouseListener(mouse_listener);
        addMouseMotionListener(mouse_listener);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        layer_manager.execute(g);

        if(tmp_command != null)
            tmp_command.execute(g);
    }

    class CanvasMouseListener extends MouseInputAdapter{

        CanvasPanel context;

        CanvasMouseListener(CanvasPanel context){
            this.context = context;
        }

        public void mousePressed(MouseEvent e){ // 눌린순간
            point_start = e.getPoint();
            //System.out.println("mousePressed : Point="+point_start);
        }   
        public void mouseDragged(MouseEvent e){ // 드래그일시
            point_drag = e.getPoint();
            //System.out.println("mouseDragged : Point="+point_drag);

            tmp_command = Command_Factory.makeCommand(point_start, point_drag, point_drag_list);
            context.repaint();

            point_drag_list.add(point_drag);

        }
        public void mouseReleased(MouseEvent e) { 
            point_end = e.getPoint();
            //System.out.println("mouseReleased : Point="+point_end);

            layer_manager.getCurLayer().add(Command_Factory.makeCommand(point_start, point_end, point_drag_list));
            context.repaint();
        
        }
    }


}