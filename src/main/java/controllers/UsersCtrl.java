package controllers;

import helpers.DatabaseConnection;
import models.User;
import models.UserType;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersCtrl {
    public UsersCtrl() {
    }

    public boolean actualizaContrasena(String code, String nuevaContrasena) {

        boolean response = false;
        String encryptedPassword = DigestUtils.sha1Hex(nuevaContrasena);

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE user SET password = ?, hasToResetPassword = 0 WHERE identificationCode = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, encryptedPassword);
            pst.setString(2, code);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;

    }

    public List<UserType> getUserTypes() {
        List<UserType> userTypes = new ArrayList<>();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM usertype";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                UserType userType = new UserType();

                userType.setUserTypeId(rs.getInt("userTypeId"));
                userType.setUserTypeName(rs.getString("userTypeName"));
                userType.setAllowedBorrowedMaterials(rs.getInt("allowedBorrowedMaterials"));

                userTypes.add(userType);
            }

            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return userTypes;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT identificationCode, email, ut.userTypeName, ut.userTypeId, phone, name FROM user u INNER JOIN userType ut ON u.userTypeId = ut.userTypeId;";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setIdentificationCode(rs.getString("identificationCode"));
                user.setEmail(rs.getString("email"));
                user.setUserTypeName(rs.getString("userTypeName"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setUserTypeId(rs.getInt("userTypeId"));

                users.add(user);
            }

            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public boolean registerUser(User user) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "INSERT INTO user(identificationCode, password, email, userTypeId, hasToResetPassword, phone, name) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, user.getIdentificationCode());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setInt(4, user.getUserTypeId());
            pst.setBoolean(5, user.isHasToResetPassword());
            pst.setString(6, user.getPhone());
            pst.setString(7, user.getName());

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean updateUser(User user, String code) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE user SET email = ?, userTypeId = ?, phone = ?, name = ? WHERE identificationCode = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, user.getEmail());
            pst.setInt(2, user.getUserTypeId());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getName());
            pst.setString(5, code);

            System.out.println(pst.toString());

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean deleteUser(String code) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "DELETE FROM user WHERE identificationCode = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, code);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean resetPassword(String code) {
        boolean response = false;
        String encryptedPassword = DigestUtils.sha1Hex("contraseña123");

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE user SET password = ?, hasToResetPassword = 1 WHERE identificationCode = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, encryptedPassword);
            pst.setString(2, code);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean checkIfUserExists(String email) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM user WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            String emailFromDb = "";

            while (rs.next()) {
                emailFromDb = rs.getString("email");
            }

            if (emailFromDb.length() != 0) {
                response = true;
            }

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean checkPassword(String email, String password) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM user WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();

            String encryptedPasswordFromDb = "";

            while (rs.next()) {
                encryptedPasswordFromDb = rs.getString("password");
            }

            if (DigestUtils.sha1Hex(password).equals(encryptedPasswordFromDb)) {
                response = true;
            }

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return response;
    }

    public User getLoggedUser(String email) {
        User user = null;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT identificationCode, email, ut.userTypeName, ut.allowedBorrowedMaterials, name FROM user u INNER JOIN usertype ut ON ut.usertypeid = u.usertypeid WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();

            user = new User();

            while (rs.next()) {
                user.setIdentificationCode(rs.getString("identificationCode"));
                user.setEmail(rs.getString("email"));
                user.setUserTypeName(rs.getString("userTypeName"));
                user.setName(rs.getString("name"));
            }

            cn.close();
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
