package Layer_DrawingBoard_JAVA;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


public class MainFrame extends JFrame{

    JPanel main_panel;

    JButton pencil_btn;
    ImageIcon pencil_btn_img = new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/pencil_btn_img.png");

    MainFrame(){
        // 타이틀 문구 설정
        setTitle("Layer_DrawingBoard");
        // 프레임 크기 설정 및 x버튼 종료 기능 지정
        setSize(960,540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널 객체화
        main_panel = new JPanel();
        main_panel.setBackground(Color.WHITE);

        
        pencil_btn = new JButton(pencil_btn_img);
        pencil_btn.setPreferredSize(new Dimension(28,28));
        pencil_btn.setBorderPainted(false);


        main_panel.add(pencil_btn);


        // 메인 프레임에 패널 부착
        add(main_panel);
        setVisible(true);
    }
}
