package Layer_DrawingBoard_JAVA;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame{

    JPanel main_panel;
    JPanel tool_panel;

    JPanel layer_panel;
    CanvasPanel canvas_panel;

    JPanel mode_panel;
    JPanel common_panel;

    static int grid_x = 1176/20;
    static int grid_y = 648/12;

    MainFrame(){
        System.out.println("MainFrame_start");

        // 타이틀 문구 설정
        setTitle("Layer_DrawingBoard");
        // (1960*3/5 , 1960*3/5)
        // 프레임 크기 설정 및 x버튼 종료 기능 지정
        setSize(1176,648);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널 객체화
        main_panel = new JPanel();
        main_panel.setLayout(null);

        Rectangle tool_rect = panel_pos(17,0,3,12);
        tool_panel = new ToolPanel();
        tool_panel.setBorder(new LineBorder(Color.lightGray,2));
        tool_panel.setBounds(tool_rect);
        main_panel.add(tool_panel);

        canvas_panel = new CanvasPanel();
        canvas_panel.setBorder(new LineBorder(Color.lightGray,2));
        canvas_panel.setBounds(panel_pos(2,2,15,10));
        main_panel.add(canvas_panel);
        canvas_panel.size = canvas_panel.getSize();


        layer_panel = new JPanel();
        layer_panel.setBorder(new LineBorder(Color.lightGray,2));
        layer_panel.setBounds(panel_pos(0,2,2,10));
        main_panel.add(layer_panel);

        mode_panel = new JPanel();
        mode_panel.setBorder(new LineBorder(Color.lightGray,2));
        mode_panel.setBounds(panel_pos(0,0,18,1));
        main_panel.add(mode_panel);

        common_panel = new JPanel();
        common_panel.setBorder(new LineBorder(Color.lightGray,2));
        common_panel.setBounds(panel_pos(0,1,18,1));
        main_panel.add(common_panel);

        

        // Tool 기본 값 셋팅
        Tool.toolInit();
        
        // 메인 프레임에 패널 부착
        add(main_panel);
        setVisible(true);
    }
    
    private static Rectangle panel_pos(int x, int y, int w, int h){
        return new Rectangle(grid_x*x, grid_y*y, grid_x*w, grid_y*h);
    }


}
