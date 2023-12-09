package controllers;

import helpers.DatabaseConnection;
import models.Editorial;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditorialCtrl {
    public EditorialCtrl() {
    }

    public List<Editorial> getEditorials() {
        List<Editorial> editorials = new ArrayList<>();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM editorial";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Editorial editorial = new Editorial();

                editorial.setEditorialId(rs.getInt("editorialId"));
                editorial.setEditorialName(rs.getString("editorialName"));

                editorials.add(editorial);
            }

            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener las editoriales. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }


        return editorials;
    }

    public Editorial getEditorial(int editorialId) {
        Editorial editorial = null;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM editorial WHERE editorialId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, editorialId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                editorial = new Editorial();

                editorial.setEditorialId(rs.getInt("editorialId"));
                editorial.setEditorialName(rs.getString("editorialName"));
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener las editoriales. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return editorial;
    }

    public boolean addEditorial(String editorialName) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "INSERT INTO editorial(editorialName) VALUES (?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, editorialName);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al agregar la editorial. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean updateCreator(String editorialName, int id) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE editorial SET editorialName = ? WHERE editorialId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, editorialName);
            pst.setInt(2, id);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar la editorial. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean deleteCreator(int id) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "DELETE FROM editorial WHERE editorialId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, id);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar la editorial. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }
}
