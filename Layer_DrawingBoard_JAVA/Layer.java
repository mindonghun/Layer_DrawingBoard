package Layer_DrawingBoard_JAVA;

import java.util.List;
import java.util.ArrayList;

import java.awt.Graphics;


public class Layer {
    List<Command> command_list;
    int start_pointer, end_pointer, redo_count;


    Layer(){
        command_list = new ArrayList<Command>();
        start_pointer = 0;
        end_pointer = 0;
        redo_count = 0;
    }

    public void execute(Graphics g){
        System.out.println("Layer - execute");
        for(int c=start_pointer; c < end_pointer; c++){
            System.out.println(c+" : "+command_list.get(c));
            command_list.get(c).execute(g);
        }
    }

    public void add(Command command){
        
        if(redo_count ==0){
            command_list.add(command);
            end_pointer++;
        }
        else{
            command_list.subList(end_pointer, command_list.size()).clear();
            command_list.add(command);
            end_pointer++;
            redo_count = 0;
        }
        
    }

    public void redo(){
        if(redo_count>0){
            end_pointer++;
            redo_count--;
        }
    }

    public void undo(){
        if(end_pointer > 0){
            end_pointer--;
            redo_count++;
        }
    }

}
