package Layer_DrawingBoard_JAVA;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.*;

import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.core.CvType.CV_8UC3;

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
        mode_command = mode_name.DrawCommand;
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

    public void set_mode_imageP(mode_name mode_name) {
        this.mode_imageP = mode_name;
    }

    // Image Processing 함수 정의
    public Mat imageP_Blur(Mat imgMat) {
        Mat dst = new Mat(imgMat.rows(), imgMat.cols(), CV_8UC3);
        Imgproc.GaussianBlur(imgMat, dst, new Size(3, 3),0,0);
        return dst;
    }

    public Mat imageP_CannyEdge(Mat imgMat) {
        Mat dst = new Mat(imgMat.rows(), imgMat.cols(), CV_8UC3);
        Imgproc.Canny(imgMat, dst,40,100);
        return dst;
    }

    public Mat imageP_Grayscale(Mat imgMat) {
        Mat dst = new Mat(imgMat.rows(), imgMat.cols(), CV_8UC3);
        System.out.println(imgMat);
        Imgproc.cvtColor(imgMat,dst,Imgproc.COLOR_BGR2GRAY);
        System.out.println(imgMat);
        return dst;
    }

    public Mat imageP_Colorinverse(Mat imgMat) {
        for(int i=0;i<imgMat.rows();i++){
            for (int j=0;j<imgMat.cols();j++){
                double[] buff = imgMat.get(i,j);
                buff[0]=255-buff[0];
                buff[1]=255-buff[1];
                buff[2]=255-buff[2];
                imgMat.put(i,j,buff);
            }
        }
        return imgMat;
    }

    public Mat imageP_Affine(Mat imgMat) {
        Point[] srcTri = new Point[3];
        srcTri[0] = new Point( 0, 0 );
        srcTri[1] = new Point( imgMat.cols() - 1, 0 );
        srcTri[2] = new Point( 0, imgMat.rows() - 1 );
        Point[] dstTri = new Point[3];
        dstTri[0] = new Point( 0, (int) (imgMat.rows()*0.33));
        dstTri[1] = new Point((int) (imgMat.cols()*0.85), (int) (imgMat.rows()*0.25));
        dstTri[2] = new Point((int) (imgMat.cols()*0.15), (int) (imgMat.rows()*0.7));
        //Mat warpMat = Imgproc.getAffineTransform(srcTri[0], dstTri);
        Mat warpDst = Mat.zeros( imgMat.rows(), imgMat.cols(), imgMat.type() );
//        Imgproc.warpAffine( imgMat, warpDst, warpMat, warpDst.size() );
        return warpDst;
    }

}
