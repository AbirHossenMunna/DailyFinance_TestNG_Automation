package Base;

public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public UserModel (String fName, String lName, String email, String password, String PhoneNumber, String address){
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = password;
        this.phoneNumber = PhoneNumber;
        this.address =address;
    }
    public UserModel(){

    }
}
