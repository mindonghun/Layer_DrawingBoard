package Layer_DrawingBoard_JAVA;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.osgi.OpenCVNativeLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.nio.Buffer;

public class ImageMat {
    Mat m_imgMat;
    BufferedImage m_tmpBuffered;
    Graphics2D g2d;
    int width, height;
    mode_name imageP_mode;

    public ImageMat() {
        width = 15*(1176/20);
        height = 10*(648/12);
        imageP_mode = Tool.getTool().mode_imageP;
        m_tmpBuffered = makeBufferedImage();
        m_imgMat = bufferedImageToMat();
    }

    // 현재 Layer의 BufferedImage 만들기
    private BufferedImage makeBufferedImage() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        g2d = img.createGraphics();

        // ImageBuffered의 Graphics Component 생성
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // BufferedImage 채워넣기
        Layer curLayer = LayerManager.getLayerManager().getCurLayer();

        // curLayer.end_pointer--;
        curLayer.execute(g2d);
        // curLayer.end_pointer++;

        return img;
    }

    // BufferedImage, Mat으로 변환
    private Mat bufferedImageToMat() {
        DataBuffer dataBuffer = m_tmpBuffered.getRaster().getDataBuffer();
        byte[] imgPixels = null;
        Mat imgMat = null;

        int width = m_tmpBuffered.getWidth();
        int height = m_tmpBuffered.getHeight();

        if(dataBuffer instanceof DataBufferByte) {
            imgPixels = ((DataBufferByte)dataBuffer).getData();
        }

        if(dataBuffer instanceof DataBufferInt) {

            int byteSize = width * height;
            imgPixels = new byte[byteSize*3];

            int[] imgIntegerPixels = ((DataBufferInt)dataBuffer).getData();

            for(int p = 0; p < byteSize; p++) {
                imgPixels[p*3 + 0] = (byte) ((imgIntegerPixels[p] & 0x00FF0000) >> 16);
                imgPixels[p*3 + 1] = (byte) ((imgIntegerPixels[p] & 0x0000FF00) >> 8);
                imgPixels[p*3 + 2] = (byte) (imgIntegerPixels[p] & 0x000000FF);
            }
        }

        if(imgPixels != null) {
            imgMat = new Mat(height, width, CvType.CV_8UC3);
            imgMat.put(0, 0, imgPixels);
        }

        return imgMat;
    }

    // Mat, BufferedImage로 변환
    private BufferedImage matToBufferedImage() {
        if (!m_imgMat.empty()) {
            int type = BufferedImage.TYPE_BYTE_GRAY;
            if (m_imgMat.channels() > 1) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            int bufferSize = m_imgMat.channels() * m_imgMat.cols() * m_imgMat.rows();
            byte[] b = new byte[bufferSize];
            m_imgMat.get(0, 0, b); // get all the pixels
            BufferedImage image = new BufferedImage(m_imgMat.cols(), m_imgMat.rows(), type);
            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            System.arraycopy(b, 0, targetPixels, 0, b.length);
            return image;
        }

        return null;
    }

    private void imageProcessing() {
        // 이미지 처리
        switch(imageP_mode) {
            case imageP_Blur:
                m_imgMat = Tool.getTool().imageP_Blur(m_imgMat);
                break;

            case imageP_CannyEdge:
                m_imgMat = Tool.getTool().imageP_CannyEdge(m_imgMat);
                break;

            case imageP_Grayscale:
                m_imgMat = Tool.getTool().imageP_Grayscale(m_imgMat);
                break;

            case imageP_Colorinverse:
                m_imgMat = Tool.getTool().imageP_Colorinverse(m_imgMat);
                break;

            case imageP_Affine:
                m_imgMat = Tool.getTool().imageP_Affine(m_imgMat);
                break;
        }

        m_tmpBuffered = matToBufferedImage();
    }
    public void run(Graphics g){
        if(imageP_mode == null)
            return;

        // image 처리 후 Draw
        imageProcessing();
        if(g2d == null)
            g2d = m_tmpBuffered.createGraphics();
        g = (Graphics) g2d;
        g.drawImage(m_tmpBuffered, 0, 0, null);

        // graphics 객체 정리
        g2d.dispose();
    }
}
