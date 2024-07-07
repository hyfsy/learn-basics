package basics;

/**
 * @author baB_hyf
 * @date 2022/09/16
 */
public class GetStaticClassName {

    public static void main(String[] args) {
        System.out.println(getClassForStatic());
    }

    public static Class<?> getClassForStatic() {
        return new Object()
        {
            public Class<?> getClassForStatic() {
                return this.getClass().getEnclosingClass();
            }
        }.getClassForStatic();
    }
}
