/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidpro02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

public class BusraGultekin23823 {

    public BusraGultekin23823() {
        createAndShowGui();
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("Resizable square");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final Rectangle myRectangle = new Rectangle(100, 75, 50,50, Color.BLUE);
        
        JLabel sliderLabel = new JLabel("Size of the square", JLabel.CENTER);
        sliderLabel.setAlignmentX(frame.CENTER_ALIGNMENT);

        JSlider jslider = new JSlider();
        jslider.setValue(50);
        jslider.setMinimum(0);
        jslider.setMaximum(100);
        jslider.setMinorTickSpacing(10);
        jslider.setMajorTickSpacing(10);
        jslider.setPaintTicks(true);
        jslider.setPaintLabels(true);
        jslider.setSnapToTicks(true);
        jslider.add(sliderLabel);
        

        jslider.setLabelTable(jslider.createStandardLabels(20));

        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);

                Graphics2D g2d = (Graphics2D) grphcs;

                myRectangle.draw(g2d);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(250, 200);
            }
        };

        jslider.addChangeListener((ChangeEvent ce) -> {
            JSlider slider = (JSlider) ce.getSource();
            
            if (!slider.getValueIsAdjusting()) {
                int newW = slider.getValue();
                int newH = slider.getValue();
                
                myRectangle.setWidth(newW);
                myRectangle.setHeight(newH);
                
                panel.repaint();
            }
        });

        frame.add(jslider, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BusraGultekin23823();
        });
    }
}

class Rectangle {
    private int x, y, width, height;
    private final Color color;

    public Rectangle(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.color = color;
    }

    void draw(Graphics2D graphic) {
        graphic.setColor(color);
        
        graphic.drawRect(x, y, width, height);
        graphic.fillRect(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}