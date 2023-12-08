package models;

public class User {

    @Override
    public String toString() {
        return "User{" +
                "identificationCode='" + identificationCode + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userTypeId=" + userTypeId +
                ", userTypeName='" + userTypeName + '\'' +
                ", hasToResetPassword=" + hasToResetPassword +
                ", phone='" + phone + '\'' +
                ", allowedBorrowedMaterials=" + allowedBorrowedMaterials +
                '}';
    }

    private String identificationCode;
    private String password;
    private String name;
    private String email;
    private int userTypeId;
    private String userTypeName;
    private boolean hasToResetPassword;
    private String phone;
    private int allowedBorrowedMaterials;

    public int getAllowedBorrowedMaterials() {
        return allowedBorrowedMaterials;
    }

    public void setAllowedBorrowedMaterials(int allowedBorrowedMaterials) {
        this.allowedBorrowedMaterials = allowedBorrowedMaterials;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public boolean isHasToResetPassword() {
        return hasToResetPassword;
    }

    public void setHasToResetPassword(boolean hasToResetPassword) {
        this.hasToResetPassword = hasToResetPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
