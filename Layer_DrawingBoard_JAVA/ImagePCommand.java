package Layer_DrawingBoard_JAVA;

import java.awt.*;

public class ImagePCommand implements Command {
    ImageMat m_img;

    ImagePCommand(ImageMat img){
        super();
        m_img = img;
    }

    @Override
    public void execute(Graphics g) { m_img.run(g); }
}
