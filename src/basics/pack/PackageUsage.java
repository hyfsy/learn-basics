package basics.pack;

import java.lang.annotation.Annotation;

/**
 * Package用法
 *
 * @author baB_hyf
 * @date 2021/04/11
 */
public class PackageUsage {

    public static void main(String[] args) throws ClassNotFoundException {

        // 获取Package对象(package-info.java)
        String pkgName = "basics.pack";
        Package pkg = Package.getPackage(pkgName); // 只能获取到当前类加载器加载过的类存在的包
        // Package pkg = PackageUsage.class.getClassLoader().getDefinedPackage(pkgName); // jdk9

        // 获取包范围的注解
        Annotation[] annotations = pkg.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof CustomPackageAnnotation) {
                System.out.println("Get package annotation: CustomPackageAnnotation");
            }
        }

        // 包内的类上是没有的
        annotations = PackageUsage.class.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof CustomPackageAnnotation) {
                System.out.println("Get class annotation: CustomPackageAnnotation");
            }
        }

        // 其他包无法使用（包括子包）
        System.out.println(Package_Class.PACKAGE_CONST);
        Package_Class.packageMethod();

        // 包加载
        pkgName = "basics.pack.t";
        pkg = Package.getPackage(pkgName);
        System.out.println("Load class before: " + pkg);

        ClassLoader classLoader = PackageUsage.class.getClassLoader();
        classLoader.loadClass("basics.pack.inner.InnerPackageClass");
        pkg = Package.getPackage(pkgName);
        System.out.println("Load class after: " + pkg);
    }
}
