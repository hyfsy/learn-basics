package basics.printname;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 动画类
 */
public class Animation {

    private JFrame frame;
    private static int x;
    private static int y;
    public static final String METHOD_PREFIX = "print";
    private java.util.List<Class<? extends AbstractChinese>> classList = new ArrayList<>();

    public Animation() {
        JFrame frame = new JFrame("写汉字");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 300);
        frame.setBackground(new Color(204, 238, 208));
    }

    /**
     * 增加一个打印字符
     */
    public void addPrint(Class<? extends AbstractChinese> clazz) {
        classList.add(clazz);
    }

    /**
     * 增加多个打印字符
     */
    public void addAllPrint(java.util.List<Class<? extends AbstractChinese>> classList) {
        this.classList.addAll(classList);
    }

    /**
     * 打印字符
     */
    public void showPrint() {
        frame.setVisible(true);
        if (classList.size() > 0) {
            JPanel panel = new MyPanel();
            frame.setContentPane(panel);
            paintChinese(panel);
        }
    }

    /**
     * 绘制汉字
     *
     * @param panel 面板对象
     */
    public void paintChinese(JPanel panel) {
        for (Class clazz : classList) {
            Method[] methods = clazz.getMethods();
            Map<String, Method> methodMap = new TreeMap<>();
            for (Method method : methods) {
                //获取所有打印的方法
                if (method.getName().startsWith(METHOD_PREFIX)) {
                    methodMap.put(method.getName(), method);
                }
            }
            try {
                Object o = clazz.getConstructor().newInstance();
                for (Map.Entry<String, Method> entry : methodMap.entrySet()) {
                    //执行方法并重新绘制
                    entry.getValue().invoke(o, panel);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 点重新绘制并睡眠一会儿
     *
     * @param panel
     */
    public static void rePaintAndStop(JPanel panel, Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
        panel.repaint();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.fillOval(x, y, 10, 10);
        }
    }

}
