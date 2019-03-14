package bookStore.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"username", "role"})})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_role_id;

    private String username;
    private String role;

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public UserRole() {
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
