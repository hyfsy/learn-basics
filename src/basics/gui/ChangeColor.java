package basics.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 渐变颜色
 */
public class ChangeColor extends JPanel implements ActionListener {

    private JFrame frame;

    public ChangeColor() {
        frame = new JFrame("渐变");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("改变颜色");
        button.addActionListener(this);
        this.add(button);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ChangeColor();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //实际是个Graphics2D对象
        Graphics2D graphics2D = (Graphics2D) g;
        //设置渐变色 （开始位置、开始颜色、结束位置、结束颜色）
        GradientPaint gradientPaint = new GradientPaint(70, 70, getRandomColor(), 300, 300, getRandomColor());
        graphics2D.setPaint(gradientPaint);
        //画椭圆（开始位置、宽高）
        graphics2D.fillOval(150, 150, 250, 250);
    }

    /**
     *
     * @return 随机的颜色对象
     */
    public Color getRandomColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        return new Color(red,green,blue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}
