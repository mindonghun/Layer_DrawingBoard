package Layer_DrawingBoard_JAVA;

import java.util.List;
import java.util.LinkedList;

import java.awt.Graphics;

public class LayerManager {
    List<Layer> layer_list;
    int cur_layer;

    LayerManager(){
        layer_list = new LinkedList<Layer>();
        layer_list.add(new Layer());
        cur_layer = 0;
        
    }

    public void execute(Graphics g){
        for(int i=0; i<layer_list.size(); i++){
            layer_list.get(i).execute(g);
        }
    }

    public Layer getCurLayer(){
        return layer_list.get(cur_layer);
    }

    public void addLayer(){
        layer_list.add(new Layer());
        cur_layer = layer_list.size() - 1;
    }

    public void switchLayer(int pos1, int pos2){
        Layer tmp_layer = layer_list.get(pos1);
        layer_list.set(pos2 , layer_list.get(pos1));
        layer_list.set(pos1 , tmp_layer);
    }
    

}
