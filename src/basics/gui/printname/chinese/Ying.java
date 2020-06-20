package basics.gui.printname.chinese;

import basics.gui.printname.Animation;
import basics.gui.printname.Coordinate;

import javax.swing.*;

public class Ying {

    public void print1(int x, int y, JPanel panel) {
        x = 100;
        y = 10;
        for (int i = 0; i < 100; i++) {
            x--;
            y++;
            Animation.rePaintAndStop(panel, new Coordinate(x, y));
        }
    }

}
