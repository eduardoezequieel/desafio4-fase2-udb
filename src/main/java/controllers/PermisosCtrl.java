package controllers;

import helpers.DatabaseConnection;
import models.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PermisosCtrl {
    public PermisosCtrl() {
    }
    
    public List<UserType> getTwoUsergetTypes() {
        List<UserType> twoUserTypes = new ArrayList<>();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT userTypeName, allowedBorrowedMaterials FROM usertype WHERE userTypeName IN ('Profesor', 'Estudiante');";
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                UserType userType = new UserType();

                userType.setUserTypeName(rs.getString("userTypeName"));
                userType.setAllowedBorrowedMaterials(rs.getInt("allowedBorrowedMaterials"));

                twoUserTypes.add(userType);
            }

            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los tipos de usuario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }

        return twoUserTypes;
    }

    public class UserTypesUpdateCtrl {

        public UserTypesUpdateCtrl() {
        }


        public void updateAllowedBorrowedMaterials(String userTypeName, int newAllowedBorrowedMaterials) {
            try {
                DatabaseConnection dbcn = new DatabaseConnection();
                Connection cn = dbcn.getConnection();


                String sql = "UPDATE usertype SET allowedBorrowedMaterials = ? WHERE userTypeName = ?";


                try (PreparedStatement pst = cn.prepareStatement(sql)) {

                    pst.setInt(1, newAllowedBorrowedMaterials);
                    pst.setString(2, userTypeName);


                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Actualización exitosa");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo realizar la actualización");
                    }
                }


                cn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar los datos. Por favor, contacta al administrador.");
                System.out.println(e.getMessage());
            }
        }
    }

    public void actualizarMaterialesPermitidos(String userTypeName, int nuevosMateriales) {
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE usertype SET allowedBorrowedMaterials = ? WHERE userTypeName = ?";
            PreparedStatement pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, nuevosMateriales);
            pstmt.setString(2, userTypeName);

            int filasActualizadas = pstmt.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Actualización exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
            }

            cn.close();
            pstmt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar los materiales permitidos. Por favor, contacte con el administrador.");
            System.out.println(e.getMessage());
        }
    }

    public List<Object[]> obtenerResultadosYearlyOverdue() {
        List<Object[]> results = new ArrayList<>();

        try {
            // Lógica para ejecutar la consulta y obtener los resultados
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT cost, year FROM yearlyOverdue;";
            try (Statement st = cn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    Object[] resultRow = {rs.getObject("cost"), rs.getObject("year")};
                    results.add(resultRow);

                }
                rs.close();
                cn.close();
            }


        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los resultados de yearlyOverdue.");
        }

        return results;
    }

    public void mostrarResultadosEnTabla(javax.swing.JTable table, List<Object[]> data, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(data.toArray(new Object[0][0]), columnNames);
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object value = model.getValueAt(row, col);

                if (col == 1) {

                    model.setValueAt(value.toString().substring(0, 4), row, col);
                }


            }
        }
        table.setModel(model);

    }

    public List<Object[]> mostrarResultadosYearlyOverdue() {
        List<Object[]> yearlyOverdueResults = new ArrayList<>();

        try {
            // Lógica para obtener y mostrar los resultados de la consulta
            yearlyOverdueResults = obtenerResultadosYearlyOverdue();

            // Definir los nombres de las columnas
            String[] columnNames = {"Costo", "Año"};


        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los resultados de yearlyOverdue.");
        }

        return yearlyOverdueResults;
    }

    public void actualizarYearlyOverdue(String year, String costo) {
        try {
            // Lógica para actualizar los datos en la base de datos
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE yearlyOverdue SET cost = ? WHERE year = ?";
            try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
                pstmt.setString(1, costo);
                pstmt.setString(2, year);

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    JOptionPane.showMessageDialog(null, "Actualización exitosa.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
                }
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar los datos. Por favor, contacta al administrador.");
            System.out.println(e.getMessage());
        }
    }

    public void updateUserType(UserType userTypeObj) {
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "UPDATE usertype SET allowedBorrowedMaterials = ? WHERE userTypeName = ?";
            try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
                pstmt.setInt(1, userTypeObj.getAllowedBorrowedMaterials());
                pstmt.setString(2, userTypeObj.getUserTypeName());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Actualización exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la actualización");
                }
            }

            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar los datos. Por favor, contacta al administrador.");
            System.out.println(e.getMessage());
        }
    }

}
