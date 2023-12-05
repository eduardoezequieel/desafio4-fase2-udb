package models;

public class UserType {
    private int userTypeId;
    private String userTypeName;
    private int allowedBorrowedMaterials;

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

    public int getAllowedBorrowedMaterials() {
        return allowedBorrowedMaterials;
    }

    public void setAllowedBorrowedMaterials(int allowedBorrowedMaterials) {
        this.allowedBorrowedMaterials = allowedBorrowedMaterials;
    }
}
