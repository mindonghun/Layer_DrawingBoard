package Layer_DrawingBoard_JAVA;

import java.awt.Graphics;

public class DrawCommand implements Command{
    Shape m_draw;

    DrawCommand(Shape shape){
        super();
        this.m_draw = shape;
    }

    @Override
    public void execute(Graphics g) {
         m_draw.draw(g);
    }
                                         
}
