package Layer_DrawingBoard_JAVA;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;


public class ImageProcessingToolPanel extends MyJPanel{

    CanvasPanel canvas_context;

    // 이미지 블러
    JButton blur_btn;
    // 엣지 탐색
    JButton edge_detect_btn;
    // 회색조
    JButton gray_scale_btn;
    // 색반전
    JButton color_reversal_btn;

    ImageProcessingToolPanel(int w, int h, CanvasPanel context) {
        super(w, h);
        canvas_context = context;

        blur_btn = new JButton("이미지 블러처리");
        blur_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.ImageProcessingCommand);
                Tool.getTool().set_mode_imageP(mode_name.imageP_Blur);
                LayerManager.getLayerManager().getCurLayer().add(Command_Factory.makeCommand(null, null, null, 0));
                canvas_context.repaint();
            }
        });

        edge_detect_btn = new JButton("엣지 탐색");
        edge_detect_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.ImageProcessingCommand);
                Tool.getTool().set_mode_imageP(mode_name.imageP_CannyEdge);
                LayerManager.getLayerManager().getCurLayer().add(Command_Factory.makeCommand(null, null, null, 0));
                canvas_context.repaint();
            }
        });

        gray_scale_btn = new JButton("회색조");
        gray_scale_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.ImageProcessingCommand);
                Tool.getTool().set_mode_imageP(mode_name.imageP_Grayscale);
                LayerManager.getLayerManager().getCurLayer().add(Command_Factory.makeCommand(null, null, null, 0));
                canvas_context.repaint();
            }
        });

        color_reversal_btn = new JButton("색반전");
        color_reversal_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.ImageProcessingCommand);
                Tool.getTool().set_mode_imageP(mode_name.imageP_Colorinverse);
                LayerManager.getLayerManager().getCurLayer().add(Command_Factory.makeCommand(null, null, null, 0));
                canvas_context.repaint();
            }
        });

        add(blur_btn);
        add(edge_detect_btn);
        add(gray_scale_btn);
        add(color_reversal_btn);

    }
}
