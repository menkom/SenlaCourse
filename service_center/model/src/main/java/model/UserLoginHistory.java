package model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.base.BaseObject;

@Entity
@Table(name = "user_login_history", schema = "service_center")
public class UserLoginHistory extends BaseObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(table = "id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(table = "login_time")
	private Date loginTime;

	@Override
	public Integer getId() {
		return id;
	}

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

	public void setId(Integer id) {
		this.id = id;
	}

}
