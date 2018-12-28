package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_login_history", schema = "service_center")
public class UserLoginHistory extends BaseObject {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((!(o instanceof UserLoginHistory)) || (!super.equals(o))) {
            return false;
        }
        UserLoginHistory that = (UserLoginHistory) o;
        return Objects.equals(getLoginTime(), that.getLoginTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLoginTime());
    }

}
