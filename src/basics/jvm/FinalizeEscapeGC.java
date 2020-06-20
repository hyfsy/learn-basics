package basics.jvm;


/**
 * 此代码演示了两点
 * 对象可以在GC时自我拯救
 * 这种自救只会有一次，因为一个对象的finalize方法只会被自动调用一次
 * <p>
 * 把自己(this 关键字) 赋值给(某个类变量)或者(对象的成员变量)，
 * 那在第二次标记时它将被移除出“即将回收（F-Queue）”的集合
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public static void isAlive() {
        System.out.println("yes我还活着");
    }

    public static void isDead() {
        System.out.println("no我死了");
    }

    public void finalize() throws Throwable {
        System.out.println("开始自救");
        super.finalize();
//        System.out.println("执行finalize方法");
        FinalizeEscapeGC.SAVE_HOOK = this;//自救
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象的第一次回收
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法的优先级很低所以暂停0.5秒等它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            FinalizeEscapeGC.isAlive();
        } else {
            FinalizeEscapeGC.isDead();
        }
        //下面的代码和上面的一样，但是这次自救却失败了
        //对象的第一次回收
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            FinalizeEscapeGC.isAlive();
        } else {
            FinalizeEscapeGC.isDead();
        }
    }

}
