package Layer_DrawingBoard_JAVA;


import org.opencv.core.Core;

public class Main{
    
    public static void main(String[] args){
        // Opencv 불러오기
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        new MainFrame();
    }
}