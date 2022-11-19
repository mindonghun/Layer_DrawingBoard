package Layer_DrawingBoard_JAVA;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.ArrayList;

public class ToolPanel extends JPanel{
    
    JPanel draw_tool;

    JPanel draw_mode;
    JButton pen_btn;
    JButton shape_btn;

    JPanel pen_tools;

    JPanel pen_shapes_panel;
    JLabel pen_shapes_label;
    List<JButton> pen_shape_list;    

    JPanel line_thickness_panel;
    JLabel line_thickness_label;
    JTextField line_thickness_textfield;

    JPanel color;
    JLabel color_label;
    List<JButton> color_list;


    JPanel shape_tools;

    ToolPanel(){
        
        draw_tool = new JPanel();
        draw_tool.setLayout(new GridLayout(2,1,0,0));

        draw_mode = new JPanel();
        draw_mode.setLayout(new FlowLayout(java.awt.FlowLayout.CENTER, 5,5));
        
        pen_btn = new JButton();
        pen_btn.setText("브러시");
        pen_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                draw_tool.removeAll();
                draw_tool.add(draw_mode);
                draw_tool.add(pen_tools);
                draw_tool.revalidate();
                draw_tool.repaint();
            }
        });
        shape_btn = new JButton();
        shape_btn.setText("도형");
        shape_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                draw_tool.removeAll();
                draw_tool.add(draw_mode);
                draw_tool.add(shape_tools);
                draw_tool.revalidate();
                draw_tool.repaint();
            }
        });
        
        draw_mode.add(pen_btn);
        draw_mode.add(shape_btn);
        
        pen_tools = new JPanel();
        pen_tools.setLayout(new GridLayout(3,1));
        
        
        pen_shapes_label = new JLabel();
        pen_shapes_label.setText("브러시 모양");
        pen_tools.add(pen_shapes_label);

        pen_shapes_panel = new JPanel();
        pen_shapes_panel.setLayout(new FlowLayout(java.awt.FlowLayout.CENTER, 5,5));
        initPenShapeList();
        for(int i=0; i<pen_shape_list.size();i++){
            pen_shapes_panel.add(pen_shape_list.get(i));
        }
        pen_tools.add(pen_shapes_panel);

        line_thickness_panel = new JPanel();
        line_thickness_panel.setLayout(new FlowLayout(java.awt.FlowLayout.CENTER, 5,5));
        line_thickness_label = new JLabel();
        line_thickness_label.setText("선 굵기");
        line_thickness_panel.add(line_thickness_label);
        line_thickness_textfield = new JTextField();
        line_thickness_textfield.setColumns(5);
        line_thickness_panel.add(line_thickness_textfield);
        pen_tools.add(line_thickness_panel);

        draw_tool.add(draw_mode);
        draw_tool.add(pen_tools);
        


        add(draw_tool);
    }

    private void initPenShapeList(){
        pen_shape_list = new ArrayList<JButton>();

        JButton pen_shape_pen = new JButton();
        pen_shape_pen.setSize(30, 30);
        pen_shape_pen.setText("팬");
        pen_shape_pen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().mode_shape = mode_name.Pen;
            }
        });
        pen_shape_list.add(pen_shape_pen);
        JButton pen_shape_pencil = new JButton();
        pen_shape_pencil.setSize(30, 30);
        pen_shape_pencil.setText("연필");
        pen_shape_pencil.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().mode_shape = mode_name.Pencil;
            }
        });
        pen_shape_list.add(pen_shape_pencil);
        JButton pen_shape_brush = new JButton();
        pen_shape_brush.setSize(30, 30);
        pen_shape_brush.setText("붓");
        pen_shape_brush.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().mode_shape = mode_name.Pencil;
            }
        });
        pen_shape_list.add(pen_shape_brush);
        JButton pen_shape_eraser = new JButton();
        pen_shape_eraser.setSize(30, 30);
        pen_shape_eraser.setText("지우개");
        pen_shape_eraser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().mode_shape = mode_name.Eraser;
            }
        });
        pen_shape_list.add(pen_shape_eraser);

    }

}
