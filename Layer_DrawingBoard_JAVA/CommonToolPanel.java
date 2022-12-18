package Layer_DrawingBoard_JAVA;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommonToolPanel extends MyJPanel{
    
    LayerManager layer_manager;

    CanvasPanel canvas_context;

    JButton redo_btn;
    JButton undo_btn;
    JButton position_change_btn;

    CommonToolPanel(int w, int h, CanvasPanel context){
        super(w,h);
        layer_manager = LayerManager.getLayerManager();
        canvas_context = context;

        redo_btn = new JButton("redo");
        redo_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                layer_manager.getCurLayer().redo();
                canvas_context.repaint();
            }
        });

        undo_btn = new JButton("undo");
        undo_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                layer_manager.getCurLayer().undo();
                canvas_context.repaint();
            }
        });

        position_change_btn = new JButton("영역 선택");
        position_change_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.PositionchangeCommand);
                Tool.getTool().set_mode_shape(mode_name.Test);
            }
        });

        
        add(undo_btn);
        add(redo_btn);
        add(position_change_btn);

    }
}
