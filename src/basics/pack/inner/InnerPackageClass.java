package basics.pack.inner;

/**
 * @author baB_hyf
 * @date 2021/04/11
 */
public class InnerPackageClass {

    public static void main(String[] args) {
        for (Package aPackage : Package.getPackages()) {
            System.out.println(aPackage);
        }
    }
}
