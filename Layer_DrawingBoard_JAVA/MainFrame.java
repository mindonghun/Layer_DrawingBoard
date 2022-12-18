package Layer_DrawingBoard_JAVA;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame{

    JPanel main_panel;
    ToolPanel tool_panel;

    JPanel layer_panel;
    CanvasPanel canvas_panel;

    CommonToolPanel common_tool_panel;
    ImageProcessingToolPanel imageProcessing_tool_panel;

    static int grid_x = 1176/20;
    static int grid_y = 648/12;

    MainFrame(){
        System.out.println("MainFrame_start");

        // Tool 기본 값 셋팅
        Tool.toolInit();
        

        // 타이틀 문구 설정
        setTitle("Layer_DrawingBoard");
        // (1960*3/5 , 1960*3/5)
        // 프레임 크기 설정 및 x버튼 종료 기능 지정
        setSize(grid_x*21,grid_y*13);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널 객체화
        main_panel = new JPanel();
        main_panel.setLayout(null);

        Rectangle tool_rect = panel_pos(17,0,3,12);
        tool_panel = new ToolPanel(tool_rect.width, tool_rect.height );
        tool_panel.setBounds(tool_rect);
        main_panel.add(tool_panel);
        
        Rectangle canvas_rect = panel_pos(2,2,15,10);
        canvas_panel = new CanvasPanel(canvas_rect.width,canvas_rect.height);
        canvas_panel.setBounds(canvas_rect);
        main_panel.add(canvas_panel);




        Rectangle layer_rect = panel_pos(0,-1,2,25);
        layer_panel = new LayerPanel(layer_rect.width, layer_rect.height, canvas_panel);
        layer_panel.setBorder(new LineBorder(Color.lightGray,2));
        layer_panel.setBounds(panel_pos(0,2,2,10));
        main_panel.add(layer_panel);

        Rectangle common_tool_rect = panel_pos(0,1,18,1);
        common_tool_panel = new CommonToolPanel(common_tool_rect.width,common_tool_rect.height, canvas_panel);
        common_tool_panel.setBounds(common_tool_rect);
        main_panel.add(common_tool_panel);

        Rectangle imageProcessing_tool_rect = panel_pos(0,0,18,1);
        imageProcessing_tool_panel = new ImageProcessingToolPanel(imageProcessing_tool_rect.width,imageProcessing_tool_rect.height, canvas_panel);
        imageProcessing_tool_panel.setBounds(imageProcessing_tool_rect);
        main_panel.add(imageProcessing_tool_panel);


        // 메인 프레임에 패널 부착
        add(main_panel);
        setVisible(true);
    }
    
    private static Rectangle panel_pos(int x, int y, int w, int h){
        return new Rectangle(grid_x*x, grid_y*y, grid_x*w, grid_y*h);
    }


}
