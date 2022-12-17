package Layer_DrawingBoard_JAVA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LayerPanel extends JPanel {
    int width,height;

    CanvasPanel canvas_context;

    List<JButton> layer_btn_list;
    JPanel layer_panel;
    MyJPanel layer_list;
    MyJPanel layer_modify_panel;

    List<Boolean> layerNumberCheck;

    LayerManager layer_manager;

    int curSelectedBtnIconNum = -1;
    int curSelectedBtnPriorityNum = -1;

    LayerPanel(int w, int h, CanvasPanel context){
        layer_manager = LayerManager.getLayerManager();

        layerNumberCheck = new ArrayList<Boolean>(List.of(false, false, false, false, false, false, false, false));

        this.width = w;
        this.height = h;

        this.canvas_context = context;

        drawLayerPanel();

        add(layer_panel);


    }

    private void drawLayerPanel(){
        layer_btn_list = new ArrayList<JButton>();
        layer_panel = new MyJPanel(width,height);
        layer_panel.removeAll();
        layerPanelLabel();
        layerPanelList();
        layerPanelModify();
    }

    private void layerPanelLabel(){
        JLabel layer_list_label = new JLabel("레이어");
        layer_list_label.setHorizontalAlignment(JLabel.CENTER);
        layer_list_label.setPreferredSize(new Dimension(width, height/24));
        layer_panel.add(layer_list_label);
    }

    private void layerPanelList(){
        layer_list = new MyJPanel(width, height/12 * 3);

        JButton label_select = layerSelectBtn(getEmptyNum());
        layer_list.add(label_select);


        layer_panel.add(layer_list);
    }

    private JButton layerSelectBtn(int emptyNum){

        JButton label_select = new JButton(new ImageIcon(new ImageIcon(
                String.format("./Layer_DrawingBoard_JAVA/Button_Image/number-%d.png",emptyNum+1))
                .getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        label_select.setPreferredSize(new Dimension(width,30));
        label_select.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int thisNum = layer_btn_list.indexOf(label_select);
                layer_manager.setCurLayer(thisNum);
                curSelectedBtnPriorityNum = thisNum;
                curSelectedBtnIconNum = emptyNum;
            }
        });
        layer_btn_list.add(label_select);
        return label_select;
    }

    private int getEmptyNum(){
        for(int j = 0; j<layerNumberCheck.size(); j++){
            if(!layerNumberCheck.get(j)){
                layerNumberCheck.set(j, true);
                return j;
            }
        }
        return -1;
    }

    private void updateLayerBtnPanel(){
        layer_list.removeAll();
        for(int i = 0; i<layer_btn_list.size(); i++){
            layer_list.add(layer_btn_list.get(i));
        }
        layer_list.revalidate();
        layer_list.repaint();
    }

    private void layerPanelModify(){
        layer_modify_panel = new MyJPanel(width, height/12);

        JButton label_add = new JButton(new ImageIcon(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/plus.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));

        label_add.setPreferredSize(new Dimension(40,40));
        label_add.addActionListener(e -> {
            int emptyNum = getEmptyNum();
            if(emptyNum != -1) {
                layer_manager.addLayer();
                System.out.println("layer added");

                layer_list.add(layerSelectBtn(emptyNum));
                updateLayerBtnPanel();
            }
        });
        JButton label_delete = new JButton(new ImageIcon(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/minus.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        label_delete.setPreferredSize(new Dimension(40,40));
        label_delete.addActionListener(e -> {
            if(curSelectedBtnIconNum > -1) {
                layer_list.remove(layer_manager.cur_layer);
                layerNumberCheck.set(curSelectedBtnIconNum, false);
                layer_btn_list.remove(layer_manager.cur_layer);
                layer_manager.removeLayer();

                curSelectedBtnIconNum = -1;

                canvas_context.repaint();

                updateLayerBtnPanel();

                System.out.println(layerNumberCheck);
            }
        });

        JButton label_up =  new JButton(new ImageIcon(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/up-arrow.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        label_up.setPreferredSize(new Dimension(40,40));
        label_up.addActionListener(e -> {
            changeLayerPriority(curSelectedBtnPriorityNum, 1);
            updateLayerBtnPanel();
        });

        JButton label_down =  new JButton(new ImageIcon(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/down-arrow.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
        label_down.setPreferredSize(new Dimension(40,40));
        label_down.addActionListener(e -> {
            changeLayerPriority(curSelectedBtnPriorityNum, -1);
            updateLayerBtnPanel();
        });

        layer_modify_panel.add(label_add);
        layer_modify_panel.add(label_delete);
        layer_modify_panel.add(label_up);
        layer_modify_panel.add(label_down);

        layer_panel.add(layer_modify_panel);
    }

    private void changeLayerPriority(int pos, int upOrDown){
        if((upOrDown == 1 && pos > 0) || (upOrDown == -1 && pos < layer_btn_list.size() - 1)) {
            JButton tmp_layer = layer_btn_list.get(pos - upOrDown);
            layer_btn_list.set(pos - upOrDown, layer_btn_list.get(pos));
            layer_btn_list.set(pos, tmp_layer);

            layer_manager.switchLayer(pos, upOrDown);
            canvas_context.repaint();
        }
    }

}
