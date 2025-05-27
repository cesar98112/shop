package api.gateway.models;

public class UserResponse {
    private String userName;
    private String name;
    private String lastName;
    private String email;
    private String roles;
    private String token;

    public UserResponse(){

    }

    public UserResponse(String userName, String name, String lastName, String email, String roles, String token) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    public UserResponse(String userName, String name, String lastName, String email, String token) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
