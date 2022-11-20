package Layer_DrawingBoard_JAVA;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommonToolPanel extends MyJPanel{
    
    LayerManager layer_manager;

    CanvasPanel canvas_context;

    JButton redo_btn;
    JButton undo_btn;
    JButton test_btn;

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


        add(redo_btn);
        add(undo_btn);


    }
}
