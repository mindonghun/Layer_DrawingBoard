package Layer_DrawingBoard_JAVA;

import org.opencv.core.Mat;

import java.awt.Color;

public class Tool {
    
    private static Tool s_instance;

    // draw mode
    public mode_name mode_command;
    public mode_name mode_shape;


    // draw
    public String line_style;
    public int line_thickness;
    public Color line_color;
    public Color fill_color;

    // Image Processing
    public mode_name mode_imageP;
    Mat result;

    Tool(){
        // mode_command = mode_name.DrawCommand;
        mode_command = mode_name.ImageProcessingCommand;
        mode_imageP = mode_name.imageP_Blur;
        mode_shape = mode_name.Rect;

        line_thickness = 1;
        line_color = Color.black;
        fill_color = Color.lightGray;
    }

    public static void toolInit(){
        s_instance = new Tool();        
    }

    public static Tool getTool(){
        return s_instance;
    }

    public void set_mode_command(mode_name mode_name){
        this.mode_command = mode_name;
    }

    public void set_mode_shape(mode_name mode_name){
        this.mode_shape = mode_name;
        PositionchangeCommand_Builder.finish_makePositionchangeCommand();
    }

    // Image Processing 함수 정의
    public Mat imageP_Blur(Mat imgMat) {
        return null;
    }

    public Mat imageP_CannyEdge(Mat imgMat) {
        return null;
    }

    public Mat imageP_Grayscale(Mat imgMat) {
        return null;
    }

    public Mat imageP_Colorinverse(Mat imgMat) {
        return null;
    }

    public Mat imageP_Affine(Mat imgMat) {
        return null;
    }

}
