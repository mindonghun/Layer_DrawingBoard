package Layer_DrawingBoard_JAVA;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import java.util.List;
import java.util.ArrayList;

public class ToolPanel extends JPanel{

    int width,height;

    JPanel draw_tool_panel;

    MyJPanel draw_mode_panel;
    JButton pen_btn;
    JButton shape_btn;

    MyJPanel pen_tools_panel;

    MyJPanel pen_shapes_panel;
    JLabel pen_shapes_label;
    List<JButton> pen_shape_list;    

    MyJPanel line_thickness_panel;
    JLabel line_thickness_label;
    JComboBox<Integer> line_thickness_JComboBox;

    MyJPanel line_color_panel;
    JLabel line_color_label;
    JButton line_color_btn;

    MyJPanel shape_tools_panel;

    MyJPanel shapes_panel;
    JLabel shapes_label;
    List<JButton> shape_list;

    MyJPanel shape_line_color_panel;
    JLabel shape_line_color_label;
    JButton shape_line_color_btn;

    MyJPanel fill_color_panel;
    JLabel fill_color_label;
    JButton fill_color_btn;

    MyJPanel shape_line_thickness_panel;
    JLabel shape_line_thickness_label;
    JComboBox<Integer> shape_line_thickness_JComboBox;

    ToolPanel(int w, int h){

        this.width = w;
        this.height = h;

        draw_tool_panel = new JPanel();
        draw_tool_panel.setPreferredSize(new Dimension(w,h));

        initDrawModePanel();
        initPenToolPanel();
        initShapeToolPanel();

        
        draw_tool_panel.add(draw_mode_panel);
        draw_tool_panel.add(shape_tools_panel);
        
        
        
        add(draw_tool_panel);
    }

    private void initDrawModePanel(){
        draw_mode_panel = new MyJPanel(width,height/12);

        pen_btn = new JButton("브러시");
        pen_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                draw_tool_panel.removeAll();
                draw_tool_panel.add(draw_mode_panel);
                draw_tool_panel.add(pen_tools_panel);
                draw_tool_panel.revalidate();
                draw_tool_panel.repaint();
            }
        });
        shape_btn = new JButton("도형");
        shape_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                draw_tool_panel.removeAll();
                draw_tool_panel.add(draw_mode_panel);
                draw_tool_panel.add(shape_tools_panel);
                draw_tool_panel.revalidate();
                draw_tool_panel.repaint();
            }
        });
        
        draw_mode_panel.add(pen_btn);
        draw_mode_panel.add(shape_btn);
    }

    private void initPenToolPanel(){
        pen_tools_panel = new MyJPanel(width,(height/12)*10);
        
        pen_shapes_label = new JLabel("브러시 모양");
        pen_tools_panel.add(pen_shapes_label);

        pen_shapes_panel = new MyJPanel(width,(height/12)*3);
        pen_shapes_panel.setLayout(new FlowLayout(java.awt.FlowLayout.CENTER, 5,5));
        initPenShapeList();
        for(int i=0; i<pen_shape_list.size();i++){
            pen_shapes_panel.add(pen_shape_list.get(i));
        }
        pen_tools_panel.add(pen_shapes_panel);

        line_thickness_panel = new MyJPanel(width,height/12);
        line_thickness_label = new JLabel("선 굵기");
        line_thickness_panel.add(line_thickness_label);
        Integer[] line_thickness_choose = {1,2,3,4,5,6,7};
        line_thickness_JComboBox = new JComboBox<>(line_thickness_choose);
        line_thickness_JComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e);
                Tool.getTool().line_thickness = line_thickness_JComboBox.getItemAt(line_thickness_JComboBox.getSelectedIndex());
            }

        });
        line_thickness_panel.add(line_thickness_JComboBox);
        pen_tools_panel.add(line_thickness_panel);


        line_color_panel = new MyJPanel(width,height/12);
        line_color_label = new JLabel("선 색");
        line_color_panel.add(line_color_label);
        line_color_btn = new JButton();
        line_color_btn.setPreferredSize(new Dimension(30,30));
        line_color_btn.setBackground(Color.black);
        line_color_btn.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);
                System.out.println(selectedColor.toString());
                if (selectedColor != null){
                    line_color_btn.setBackground(selectedColor);
                    Tool.getTool().line_color = selectedColor;
                    shape_line_color_btn.setBackground(selectedColor);
                }
                
            }
        });
        line_color_panel.add(line_color_btn);
        pen_tools_panel.add(line_color_panel);
    }

    private void initShapeToolPanel(){
        shape_tools_panel = new MyJPanel(width,(height/12)*10);

        shapes_label = new JLabel("도형 모양");
        shape_tools_panel.add(shapes_label);
        
        shapes_panel = new MyJPanel(width, (height/12)*3);
        shapes_panel.setLayout(new FlowLayout(java.awt.FlowLayout.CENTER, 5,5));
        initShapeList();
        for(int i=0; i<shape_list.size();i++){
            shapes_panel.add(shape_list.get(i));
        }
        shape_tools_panel.add(shapes_panel);

        shape_line_thickness_panel = new MyJPanel(width,height/12);
        shape_line_thickness_label = new JLabel("선 굵기");
        shape_line_thickness_panel.add(shape_line_thickness_label);
        Integer[] shape_line_thickness_choose = {1,2,3,4,5,6,7};
        shape_line_thickness_JComboBox = new JComboBox<>(shape_line_thickness_choose);
        shape_line_thickness_JComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(e);
                Tool.getTool().line_thickness = shape_line_thickness_JComboBox.getItemAt(shape_line_thickness_JComboBox.getSelectedIndex());
            }

        });
        shape_line_thickness_panel.add(shape_line_thickness_JComboBox);
        shape_tools_panel.add(shape_line_thickness_panel);

        shape_line_color_panel = new MyJPanel(width,height/12);
        shape_line_color_label = new JLabel("선 색");
        shape_line_color_panel.add(shape_line_color_label);
        shape_line_color_btn = new JButton();
        shape_line_color_btn.setPreferredSize(new Dimension(30,30));
        shape_line_color_btn.setBackground(Color.black);
        shape_line_color_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);
                System.out.println(selectedColor.toString());
                if (selectedColor != null){
                    Tool.getTool().line_color = selectedColor;
                    line_color_btn.setBackground(selectedColor);
                    shape_line_color_btn.setBackground(selectedColor);
                }
                
            }
        });
        shape_line_color_panel.add(shape_line_color_btn);
        shape_tools_panel.add(shape_line_color_panel);

        fill_color_panel = new MyJPanel(width,height/12);
        fill_color_label = new JLabel("채움 색");
        fill_color_panel.add(fill_color_label);
        fill_color_btn = new JButton();
        fill_color_btn.setPreferredSize(new Dimension(30,30));
        fill_color_btn.setBackground(Color.lightGray);
        fill_color_btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW);
                System.out.println(selectedColor.toString());
                if (selectedColor != null){
                    Tool.getTool().fill_color = selectedColor;
                    fill_color_btn.setBackground(selectedColor);
                }
                
            }
        });
        fill_color_panel.add(fill_color_btn);
        shape_tools_panel.add(fill_color_panel);


    }

    private void initPenShapeList(){
        pen_shape_list = new ArrayList<JButton>();

        JButton pen_shape_pen = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/pen_btn_img.png"));
        pen_shape_pen.setPreferredSize(new Dimension(30,30));
        pen_shape_pen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Pen);
            }
        });
        pen_shape_list.add(pen_shape_pen);
        JButton pen_shape_pencil = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/pencil_btn_img.png"));
        pen_shape_pencil.setPreferredSize(new Dimension(30,30));
        pen_shape_pencil.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Pencil);
            }
        });
        pen_shape_list.add(pen_shape_pencil);
        JButton pen_shape_brush = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/brush_btn_img.png"));
        pen_shape_brush.setPreferredSize(new Dimension(30,30));
        pen_shape_brush.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Pencil);
            }
        });
        pen_shape_list.add(pen_shape_brush);
        JButton pen_shape_eraser = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/eraser_btn_img.png"));
        pen_shape_eraser.setPreferredSize(new Dimension(30,30));
        pen_shape_eraser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Eraser);
            }
        });
        pen_shape_list.add(pen_shape_eraser);

    }

    private void initShapeList(){
        shape_list = new ArrayList<JButton>();

        JButton shape_line = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/line_btn_img.png"));
        shape_line.setPreferredSize(new Dimension(30,30));
        shape_line.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Line);
            }
        });
        shape_list.add(shape_line);

        JButton shape_rect = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/rect_btn_img.png"));
        shape_rect.setPreferredSize(new Dimension(30,30));
        shape_rect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Rect);
            }
        });
        shape_list.add(shape_rect);

        JButton shape_oval = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/oval_btn_img.png"));
        shape_oval.setPreferredSize(new Dimension(30,30));
        shape_oval.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.DrawCommand);
                Tool.getTool().set_mode_shape(mode_name.Oval);
            }
        });
        shape_list.add(shape_oval);

        JButton shape_test = new JButton(new ImageIcon("./Layer_DrawingBoard_JAVA/Button_Image/test_img.png"));
        shape_test.setPreferredSize(new Dimension(30,30));
        shape_test.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.getTool().set_mode_command(mode_name.PositionchangeCommand);
                Tool.getTool().set_mode_shape(mode_name.Test);
            }
        });
        shape_list.add(shape_test);

    }

}
