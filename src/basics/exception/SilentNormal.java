package basics.exception;

import java.io.IOException;

/**
 * @author baB_hyf
 * @date 2021/01/06
 */
public class SilentNormal {

    public static RuntimeException rethrow(Throwable e) {
        return uncheckedThrow(e);
    }

    /**
     * 抛不抛类型转换失败 取决于走不走外部方法调用 return
     * <p>
     * 直接抛出异常则不走
     */
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T uncheckedThrow(Throwable e) throws T {
        throw (T) e;
        // return (T) e;
    }

    public static void main(String[] args) {
        IOException ioException = new IOException();
        rethrow(ioException);
    }
}
