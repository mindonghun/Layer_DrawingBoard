package Layer_DrawingBoard_JAVA;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasPanel extends JPanel{
    Dimension size;
    Point p_s, p_e;
    List<Command> command_list;


    CanvasPanel(){
        System.out.println("CanvaslPanel");

        command_list = new ArrayList<Command>();

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
        }
        public void mouseReleased(MouseEvent e) {
            Point point = e.getPoint();
            System.out.println("mouseReleased : Point="+point);
            p_e = e.getPoint();
            
            command_list.add(new DrawCommand(new Line(p_s,p_e)));
            context.repaint();   
        }
    }


}