package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.Date;

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
    public boolean equals(Object other) {
        UserLoginHistory userLoginHistory = (UserLoginHistory) other;
        return super.equals(other)
                && isEqual(loginTime, userLoginHistory.getLoginTime())
                && isEqual(user, userLoginHistory.getUser());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), loginTime);
        return objHash(result, user);
    }

}
