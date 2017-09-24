package com.bookstore.domain.security;
import com.bookstore.domain.User;
import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userRoleId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id")
   User user;

   @ManyToOne(fetch =FetchType.EAGER)
   @JoinColumn(name = "role_id")
   Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
