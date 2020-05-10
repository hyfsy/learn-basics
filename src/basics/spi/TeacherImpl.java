package basics.spi;

/**
 * @author baB_hyf
 * @date 2020/02/26
 */
public class TeacherImpl implements PersonI {
    @Override
    public void say() {
        System.out.println("teach child");
    }
}
