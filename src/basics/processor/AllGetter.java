package basics.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author baB_hyf
 * @date 2020/07/04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface AllGetter {
}
