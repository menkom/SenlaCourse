package info.mastera.model.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = -9131179498317687352L;

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
        return (getId() != null)
                ? (getClass().getSimpleName().hashCode() + getId().hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (getId() == null) return false;

        return getId().equals(((BaseObject) other).getId());
    }
}
