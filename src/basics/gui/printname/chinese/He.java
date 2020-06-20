package basics.gui.printname.chinese;

import basics.gui.printname.AbstractChinese;

import javax.swing.*;

public class He extends AbstractChinese {

    public void print1(JPanel panel) {
        super.x = 100;
        y = 10;
        for (int i = 0; i < 100; i++) {
            x--;
            y++;
            rePaint(panel);
        }
    }

    public void print2(JPanel panel) {
        x = 60;
        y = 50;
        for (int i = 0; i < 150; i++) {
            y++;
            rePaint(panel);
        }
    }

    public void print3(JPanel panel) {
        x = 60;
        y = 50;
        for (int i = 0; i < 200; i++) {
            x++;
            rePaint(panel);
        }
    }

    public void print4(JPanel panel) {
        x = 100;
        y = 80;
        for (int i = 0; i < 90; i++) {
            y++;
            rePaint(panel);
        }
    }

    public void print5(JPanel panel) {
        x = 100;
        y = 80;
        for (int i = 0; i < 80; i++) {
            x++;
            rePaint(panel);
        }
    }

    public void print6(JPanel panel) {
        x = 180;
        y = 80;
        for (int i = 0; i < 90; i++) {
            y++;
            rePaint(panel);
        }
    }

    public void print7(JPanel panel) {
        x = 100;
        y = 160;
        for (int i = 0; i < 80; i++) {
            x++;
            rePaint(panel);
        }
    }

    public void print8(JPanel panel) {
        x = 240;
        y = 50;
        for (int i = 0; i < 160; i++) {
            y++;
            rePaint(panel);
        }
    }

    public void print9(JPanel panel) {
        x = 240;
        y = 210;
        for (int i = 0; i < 20; i++) {
            x--;
            y--;
            rePaint(panel);
        }
    }

}
