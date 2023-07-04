package guidpro03;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

public class BusraGultekin23823 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Start("Color selector");
        });
    }
}

class Draw extends JPanel {

    Color colorBack, colorFore;

    public Draw() {
        colorFore = Color.BLACK;
        colorBack = Color.BLACK;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.setColor(colorBack);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(colorFore);
        gr.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
        gr.fillOval(getWidth() / 2 - 30, getHeight() / 2 - 30, 60, 60);
    }
}

class Start extends JFrame implements ChangeListener {

    Panel back;
    Panel fore;
    Draw drawing = new Draw();

    public Start(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                titleAlign();
            }

        });

        back = new Panel("Background", this);
        fore = new Panel("Foreground", this);

        add(back, BorderLayout.WEST);
        add(drawing, BorderLayout.CENTER);
        add(fore, BorderLayout.EAST);
        
        pack();
        setVisible(true);
    }

    private void titleAlign() {
        Font font = getFont();
        String currentTitle = getTitle().trim();
        FontMetrics fm = getFontMetrics(font);
        int frameWidth = getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        String pad = "";
        pad = String.format("%" + (spaceCount - 14) + "s", pad);
        setTitle(pad + currentTitle);

    }

    @Override
    public void stateChanged(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        if (source == back.R) {
            Color color = new Color(source.getValue(), back.G.getValue(), back.B.getValue());
            back.labelH.setText(String.format("#%02X%02X%02X", source.getValue(), back.G.getValue(), back.B.getValue()));
            back.labelR.setText(String.format("%03d", source.getValue()));
            drawing.colorBack = color;
        } else if (source == back.G) {
            Color color = new Color(back.R.getValue(), source.getValue(), back.B.getValue());
            back.labelH.setText(String.format("#%02X%02X%02X", back.R.getValue(), source.getValue(), back.B.getValue()));
            back.labelG.setText(String.format("%03d", source.getValue()));
            drawing.colorBack = color;
        } else if (source == back.B) {
            Color color = new Color(back.R.getValue(), back.G.getValue(), source.getValue());
            back.labelH.setText(String.format("#%02X%02X%02X", back.R.getValue(), back.G.getValue(), source.getValue()));
            back.labelB.setText(String.format("%03d", source.getValue()));
            drawing.colorBack = color;
        } else if (source == fore.R) {
            Color color = new Color(source.getValue(), fore.G.getValue(), fore.B.getValue());
            fore.labelH.setText(String.format("#%02X%02X%02X", source.getValue(), fore.G.getValue(), fore.B.getValue()));
            fore.labelR.setText(String.format("%03d", source.getValue()));
            drawing.colorFore = color;
        } else if (source == fore.G) {
            Color color = new Color(fore.R.getValue(), source.getValue(), fore.B.getValue());
            fore.labelH.setText(String.format("#%02X%02X%02X", fore.R.getValue(), source.getValue(), fore.B.getValue()));
            fore.labelG.setText(String.format("%03d", source.getValue()));
            drawing.colorFore = color;
        } else if (source == fore.B) {
            Color color = new Color(fore.R.getValue(), fore.G.getValue(), source.getValue());
            fore.labelH.setText(String.format("#%02X%02X%02X", fore.R.getValue(), fore.G.getValue(), source.getValue()));
            fore.labelB.setText(String.format("%03d", source.getValue()));
            drawing.colorFore = color;
        }
        repaint();
    }
}

class Panel extends JPanel {

    JPanel panelR = new JPanel();
    JSlider R = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    JLabel labelR = new JLabel("000");

    JPanel panelG = new JPanel();
    JSlider G = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    JLabel labelG = new JLabel("000");

    JPanel panelB = new JPanel();
    JSlider B = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    JLabel labelB = new JLabel("000");
    JLabel labelH = new JLabel("#000000");

    TitledBorder titledBorder;

    public Panel(String title, Start parent) {
        R.addChangeListener(parent);
        G.addChangeListener(parent);
        B.addChangeListener(parent);

        panelR.setLayout(new BoxLayout(panelR, BoxLayout.Y_AXIS));
        panelG.setLayout(new BoxLayout(panelG, BoxLayout.Y_AXIS));
        panelB.setLayout(new BoxLayout(panelB, BoxLayout.Y_AXIS));

        panelR.add(R);
        panelG.add(G);
        panelB.add(B);

        panelR.add(labelR);
        panelG.add(labelG);
        panelB.add(labelB);

        titledBorder = BorderFactory.createTitledBorder("R");
        panelR.setBorder(titledBorder);

        titledBorder = BorderFactory.createTitledBorder("G");
        panelG.setBorder(titledBorder);

        titledBorder = BorderFactory.createTitledBorder("B");
        panelB.setBorder(titledBorder);

        JPanel panelH = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Hex");
        panelH.setBorder(titledBorder);
        labelH.setForeground(Color.GRAY);
        panelH.add(labelH);

        JPanel panelRGB = new JPanel();
        panelRGB.add(panelR);
        panelRGB.add(panelG);
        panelRGB.add(panelB);

        add(panelRGB);
        add(panelH);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        setBorder(BorderFactory.createTitledBorder(titledBorder));
    }
}
