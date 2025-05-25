package api.authentication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Document(value = "Users")
public class UserModel {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private Set<Role> roles;
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialsNoExpired;



    public UserModel(){

    }

    public UserModel(String id, String username, String password , String email, String name, String lastname, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.name = name;
        this.roles = roles;
    }
    public UserModel(String username, String password,String email, String name, String lastname, Set<Role> roles) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(rol -> "Role_"+rol)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(boolean accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public boolean isCredentialsNoExpired() {
        return credentialsNoExpired;
    }

    public void setCredentialsNoExpired(boolean credentialsNoExpired) {
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(boolean accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
