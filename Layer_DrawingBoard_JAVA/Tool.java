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

    public static void toolInit(){
        s_instance = new Tool();

        s_instance.mode_command = mode_name.DrawCommand;
        s_instance.mode_shape = mode_name.Rect;

        s_instance.line_thickness = 3;
        s_instance.line_color = Color.black;
        s_instance.fill_color = Color.lightGray;
        
    }

    public static Tool getTool(){
        return s_instance;
    }

}
