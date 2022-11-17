package Layer_DrawingBoard_JAVA;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasPanel extends JPanel{
    Dimension size;
    Point p_s, p_e, p_d;
    Set<Point> p_d_list;
    List<Command> command_list;
    Command tmp_command = null;

    CanvasPanel(){
        System.out.println("CanvaslPanel");

        command_list = new ArrayList<Command>();
        p_d_list = new HashSet<Point>();

        setBackground(Color.WHITE);
        addMouseListener(new MyMouseListener(this));
        addMouseMotionListener(new MyMouseListener(this));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        for(int c=0; c<command_list.size(); c++){
            command_list.get(c).execute(g);
        }
        if(tmp_command != null)
            tmp_command.execute(g);
    }

    class MyMouseListener extends MouseInputAdapter{

        CanvasPanel context;

        MyMouseListener(CanvasPanel context){
            this.context = context;
        }

        public void mousePressed(MouseEvent e){ // 눌린순간
            Point point = e.getPoint();
            System.out.println("mousePressed : Point="+point);
            p_s = e.getPoint();
        }   
        public void mouseDragged(MouseEvent e){ // 드래그일시
            Point point = e.getPoint();
            System.out.println("mouseDragged : Point="+point);
            p_d = e.getPoint();
            //tmp_command = finalize();
            tmp_command = Command_Factory.makeCommand(p_s, p_d, p_d_list);
            context.repaint();

            p_d_list.add(p_d);

        }
        public void mouseReleased(MouseEvent e) {
            Point point = e.getPoint();
            System.out.println("mouseReleased : Point="+point);
            p_e = e.getPoint();

            command_list.add(Command_Factory.makeCommand(p_s, p_e, p_d_list));
            context.repaint();
        
        }
    }


}