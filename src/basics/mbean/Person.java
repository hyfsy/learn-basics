package basics.mbean;

/**
 * @author baB_hyf
 * @date 2020/06/16
 */
public class Person implements PersonMBean {

    private Integer id;
    private String name;
    private Integer age;

    /**
     * 不能覆盖
     */
    public Person() {}

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void say() {
        System.out.println("hello mbean");
    }
}
