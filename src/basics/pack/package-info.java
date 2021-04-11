/**
 * JavaDoc上会展示此包描述信息
 * <p>
 * 该文件也可定义为package-info.html，内容为描述信息，一般也都是这样，因为java文件没啥用
 *
 * @author baB_hyf
 * @date 2021/04/11
 */
@CustomPackageAnnotation
@Deprecated // 标记整个包过时
package basics.pack;

/**
 * 包范围的类、常量，和default有啥区别？？？
 */
class Package_Class {

    public static final String PACKAGE_CONST = "PACKAGE_CONST";

    public static void packageMethod() {
        System.out.println("Invoke package method success!");
    }
}