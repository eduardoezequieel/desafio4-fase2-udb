package controllers;

import helpers.DatabaseConnection;
import models.Creator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatorCtrl {
    public CreatorCtrl() {
    }

    public List<Creator> getCreators() {
        List<Creator> creators = new ArrayList<>();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM creator";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Creator creator = new Creator();

                creator.setCreatorId(rs.getInt("creatorId"));
                creator.setCreatorName(rs.getString("creatorName"));

                creators.add(creator);
            }

            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los creadores. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return creators;
    }

    public Creator getCreator(int creatorId) {
        Creator creator = null;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT*FROM creator WHERE creatorId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, creatorId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                creator = new Creator();

                creator.setCreatorId(rs.getInt("creatorId"));
                creator.setCreatorName(rs.getString("creatorName"));
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los creadores. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return creator;
    }

    public boolean addCreator(String creatorName) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "INSERT INTO creator(creatorName) VALUES (?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, creatorName);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al agregar al creador. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean updateCreator(String creatorName, int id) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE creator SET creatorName = ? WHERE creatorId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, creatorName);
            pst.setInt(2, id);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar al creador. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }

    public boolean deleteCreator(int id) {
        boolean response = false;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "DELETE FROM creator WHERE creatorId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, id);

            if (!pst.execute()) response = true;

            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar al creador. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return response;
    }
}
