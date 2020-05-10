package basics.printname;

import javax.swing.*;

/**
 * 绘制的汉字都要继承该抽象汉字类
 */
public abstract class AbstractChinese {

    protected int x;
    protected int y;
    private Coordinate coordinate = new Coordinate();

    public void setXY(int x, int y) {
        coordinate.setX(x);
        coordinate.setY(y);
    }

    protected final void rePaint(JPanel panel) {
        setXY(x, y);
        Animation.rePaintAndStop(panel, coordinate);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
