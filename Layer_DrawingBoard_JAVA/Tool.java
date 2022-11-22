package Layer_DrawingBoard_JAVA;

import java.awt.Color;

public class Tool {
    
    private static Tool s_instance;

    public mode_name mode_command;
    public mode_name mode_shape;


    // draw
    public String line_style;
    public int line_thickness;
    public Color line_color;
    public Color fill_color;

    Tool(){
        mode_command = mode_name.DrawCommand;
        mode_shape = mode_name.Rect;

        line_thickness = 3;
        line_color = Color.black;
        fill_color = Color.lightGray;
    }

    public static void toolInit(){
        s_instance = new Tool();        
    }

    public static Tool getTool(){
        return s_instance;
    }

}
