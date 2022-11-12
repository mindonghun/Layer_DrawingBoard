package Layer_DrawingBoard_JAVA;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;




import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;

public class MainFrame extends JFrame{

    JPanel main_panel;



    JButton pencil_btn;
    ImageIcon pencil_btn_img = new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/pencil_btn_img.png");

    MainFrame(){
        System.out.println("MainFrame_start");

        // 타이틀 문구 설정
        setTitle("Layer_DrawingBoard");
        // 프레임 크기 설정 및 x버튼 종료 기능 지정
        setSize(960,540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널 객체화
        main_panel = new JPanel();
        
        pencil_btn = new JButton(pencil_btn_img);
        pencil_btn.setPreferredSize(new Dimension(28,28));
        pencil_btn.setBorderPainted(false);


        main_panel.add(pencil_btn);

        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseListener());
        // 메인 프레임에 패널 부착
        add(main_panel);
        setVisible(true);
    }
    

    class MyMouseListener extends MouseAdapter{
        public void mousePressed(MouseEvent e){ // 눌린순간
            int x = e.getX();
            int y = e.getY();
            System.out.println("mousePressed : Point("+x+","+y+")");
        }   
        public void mouseDragged(MouseEvent e){ // 드래그일시
            int x = e.getX();
            int y = e.getY();
            System.out.println("mouseDragged : Point("+x+","+y+")");
        }
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println("mouseReleased : Point("+x+","+y+")");
        }
    }



}
