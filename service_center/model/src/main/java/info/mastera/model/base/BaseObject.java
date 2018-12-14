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
        return (other != null && getId() != null
                && other.getClass().isAssignableFrom(getClass())
                && getClass().isAssignableFrom(other.getClass()))
                ? getId().equals(((BaseObject) other).getId())
                : (other == this);
    }
}
