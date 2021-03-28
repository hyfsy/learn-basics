package test.serialize;

import java.io.Serializable;

/**
 * @author baB_hyf
 * @date 2021/03/22
 */
public class Per implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Per{" +
                "id=" + id +
                '}';
    }
}
