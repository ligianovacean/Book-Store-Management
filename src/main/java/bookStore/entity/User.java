package bookStore.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(unique = true)
    private String username;
    private String password;
    private byte enabled;

    public User() {
    }

    public User(String username, String password, byte enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }
}
