package Layer_DrawingBoard_JAVA;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class MyJPanel extends JPanel{
    MyJPanel(int w, int h){
        this.setPreferredSize(new Dimension(w,h));
        this.setBorder(new LineBorder(Color.lightGray,2));
        this.setBackground(Color.WHITE);
    }
}
