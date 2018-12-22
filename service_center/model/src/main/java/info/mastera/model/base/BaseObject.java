package info.mastera.model.base;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = -9131179498317687352L;

    private static final int prime = 31;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + getId() + "; ";
    }

    @Override
    public int hashCode() {
        return objHash(1,id);
    }

    protected int objHash(int hash, Object obj) {
        return prime * hash + ((obj == null) ? 0 : obj.hashCode());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        BaseObject baseObject = (BaseObject) other;
        return isEqual(id, baseObject.getId());
    }

    protected boolean isEqual(Object obj1, Object obj2) {
        return (obj1 != null && obj1.equals(obj2));
    }

}
