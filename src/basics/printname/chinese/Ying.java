package basics.printname.chinese;

import basics.printname.Animation;
import basics.printname.Coordinate;

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
