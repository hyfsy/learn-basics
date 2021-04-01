package basics.jmx.mbean;

/**
 * @author baB_hyf
 * @date 2020/06/16
 */
public interface PersonMBean {

    Integer getId();

    String getName();

    Integer getAge();

    void setAge(Integer age);

    void say();
}
