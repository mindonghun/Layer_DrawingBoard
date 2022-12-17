package Layer_DrawingBoard_JAVA;

import java.util.List;
import java.util.LinkedList;

import java.awt.Graphics;

public class LayerManager {

    private static LayerManager layer_manager;

    List<Layer> layer_list;
    int cur_layer;
    int canvas_width, canvas_height;

    LayerManager(int w, int h){
        canvas_width = w;
        canvas_height = h;
        layer_list = new LinkedList<Layer>();
        layer_list.add(new Layer());
        cur_layer = 0;
        
    }

    public static void LayerManagerInit(int w, int h){
        layer_manager = new LayerManager(w,h);

    }

    public static LayerManager getLayerManager(){
        return layer_manager;
    }

    public void execute(Graphics g){
        for(int i=layer_list.size() - 1; i >= 0; i--){
            if(layer_list.get(i).getVisibility()) {
                layer_list.get(i).execute(g);
            }
        }
    }

    public Layer getCurLayer(){
        return layer_list.get(cur_layer);
    }

    public void addLayer(){
        layer_list.add(new Layer());
        cur_layer = layer_list.size() - 1;
    }

    public void switchLayer(int pos, int upOrDown){
        if((upOrDown == 1 && pos > 0) || (upOrDown == -1 && pos < layer_list.size() - 1)) {
            Layer tmp_layer = layer_list.get(pos - upOrDown);
            layer_list.set(pos - upOrDown, layer_list.get(pos));
            layer_list.set(pos, tmp_layer);
        }
    }

    public void removeLayer(){
        layer_list.remove(cur_layer);
        cur_layer = layer_list.size() - 1;
        System.out.println("cur_layer_list_size : " + layer_list.size());
    }

    public void setCurLayer(int l){
        cur_layer = l;
        System.out.println(cur_layer);
    }
    

}
