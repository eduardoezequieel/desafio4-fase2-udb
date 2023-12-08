package controllers;

import helpers.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Loan;

public class LoanCtrl {
    public LoanCtrl() {}
    
    public List<Loan> getActiveLoans() {
        List<Loan> loans = new ArrayList<>();
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT loanId, ls.loanStateName, u.name as responsable, u2.name as admin, startDate, endDate, lo.price, lo.pendingDays FROM loan l "
                    + "INNER JOIN loanState ls ON ls.loanStateId = l.loanStateId "
                    + "INNER JOIN user u ON l.userId = u.identificationCode "
                    + "LEFT JOIN user u2 ON l.adminId = u2.identificationCode "
                    + "LEFT JOIN loanOverdue lo ON  lo.loanOverdueId = l.loanOverdueId;";

        
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setLoanStateName(rs.getString("loanStateName"));
                loan.setResponsable(rs.getString("responsable"));
                loan.setAdmin(rs.getString("admin"));
                loan.setStartDate(rs.getString("startDate"));
                loan.setEndDate(rs.getString("endDate"));
                loan.setPrice(rs.getString("price"));
                loan.setPendingDays(rs.getInt("pendingDays"));
                
                loans.add(loan);
            }
            
            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al obtener los prestamos.");
        }
        
        return loans;
    }
    
    public int materialIsAlreadyBorrowed(int inventoryId) {
        int response = 0;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT l.loanId, i.inventoryId, ls.loanStateName\n" +
                        "FROM borrowedmaterial b\n" +
                        "INNER JOIN loan l ON l.loanId = b.loanId\n" +
                        "INNER JOIN loanstate ls ON ls.loanStateId = l.loanStateId\n" +
                        "INNER JOIN inventory i ON b.inventoryMaterialId = i.inventoryId\n" +
                        "WHERE (ls.loanStateId = 1 OR ls.loanStateId = 2 OR ls.loanStateId = 3) AND i.inventoryId = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, inventoryId);
            
            ResultSet rs = pst.executeQuery();
            
            String loanStateName = "";
            
            while(rs.next()) {
                loanStateName = rs.getString("loanStateName");
            }
            
            if (loanStateName.length() > 0) {
                response = 1;
            }
            
            cn.close();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al verificar si el material esta en un prestamo activo. Por favor contacta con el administrador.");
            response = -1;
        }
        
        return response;
    }
    
    public int checkIfUserHasAPendingLoan(String code) {
        int response = 0;

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT * FROM loan WHERE userId = ? AND (loanStateId = 2 OR loanStateId = 3)";

            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, code);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String endDateString = rs.getString("endDate");
                int loanId = rs.getInt("loanId");

                LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Obtener la fecha actual
                LocalDate currentDate = LocalDate.now();

                // Comparar las fechas
                if (endDate.isBefore(currentDate)) {
                    response = 1;
                    markLoanWithFee(loanId);
                } else {
                    // La fecha aún no ha vencido
                    response = 0;
                }
            }

            cn.close();
            pst.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = -1;
        }

        return response;
    }
    
    public int getBorrowedMaterialsQuantity(String code) {
        int response = -1;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT COUNT(borrowedMaterialId) as materials FROM borrowedmaterial b "
                    + "INNER JOIN loan l ON l.loanId = b.loanId "
                    + "WHERE l.userId = ? AND (l.loanStateId = 1 OR l.loanStateId = 2 OR l.loanStateId = 3)";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, code);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                response = rs.getInt("materials");
            }
            
            cn.close();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al verificar si el material esta en un prestamo activo. Por favor contacta con el administrador.");
        }
        
        return response;
    }
    
    public String getMaterialsFromLoan(int loanId) {
        StringBuilder materials = new StringBuilder();

        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();

            String sql = "SELECT i.inventoryId, m.title, mt.materialTypeName, l.libraryName FROM borrowedmaterial b \n" +
                    "INNER JOIN inventory i ON i.inventoryId = b.inventoryMaterialId \n" +
                    "INNER JOIN material m ON m.code = i.materialCode \n" +
                    "INNER JOIN materialType mt ON m.materialTypeId = mt.materialTypeId\n" +
                    "INNER JOIN library l ON l.libraryId = i.libraryId WHERE loanId = ?";

            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setInt(1, loanId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                materials.append("ID: ").append(rs.getString("inventoryId")).append("\n");
                materials.append("Título: ").append(rs.getString("title")).append("\n");
                materials.append("Tipo de material: ").append(rs.getString("materialTypeName")).append("\n");
                materials.append("Ubicación: ").append(rs.getString("libraryName")).append("\n");
                materials.append("\n"); // Separador entre cada conjunto de datos
            }

            cn.close();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al verificar si el material esta en un prestamo activo. Por favor contacta con el administrador.");
        }

        return materials.toString();
    }
    
    public boolean createLoan(Loan loan) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "INSERT INTO loan(loanStateId, userId, startDate, endDate) VALUES (?,?,?,?)";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, 1);
            pst.setString(2, loan.getUserId());
            pst.setString(3, loan.getStartDate());
            pst.setString(4, loan.getEndDate());
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al crear el prestamo. Por favor contacta con el administrador.");
        }
        
        return response;
    }
    
    public boolean approveLoan(int loanId, String identificationCode) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "UPDATE loan SET loanStateId = 2, adminId = ? WHERE loanId = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, identificationCode);
            pst.setInt(2, loanId);
            
            System.out.println(pst.toString());
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al aprobar el prestamo. Por favor contacta con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public boolean markLoanWithFee(int loanId) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "UPDATE loan SET loanStateId = 3 WHERE loanId = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, loanId);
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al aprobar el prestamo. Por favor contacta con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public double getCurrentFee(String year) {
        double fee = -1;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM yearlyoverdue WHERE year = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, year);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                fee = rs.getFloat("cost");
            }
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al obtener la mora de este año. Por favor contacta con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return fee;
    }
    
    public boolean addFeeToLoan(int loanId, double price, long pendingDays) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String insertionSql = "INSERT INTO loanoverdue(price, pendingDays) VALUES(?,?)";
            
            PreparedStatement pst = cn.prepareStatement(insertionSql);
            
            pst.setDouble(1, price);
            pst.setLong(2, pendingDays);
            
            if(!pst.execute()) {
                int loanOverdueId = 0;
                String loanOverdueQuery = "SELECT*FROM loanoverdue ORDER BY loanOverdueId DESC LIMIT 1";
                
                Statement st = cn.createStatement();
                
                ResultSet rs = st.executeQuery(loanOverdueQuery);
                
                while(rs.next()) {
                    loanOverdueId = rs.getInt("loanOverdueId");
                }
                
                st.close();
                rs.close();
                
                String updateQuery = "UPDATE loan SET loanOverdueId = ? WHERE loanId = ?";
                PreparedStatement pst2 = cn.prepareStatement(updateQuery);
                
                pst2.setInt(1, loanOverdueId);
                pst2.setInt(2, loanId);
                
                if (!pst2.execute()) response = true;
                
                pst2.close();
            }
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al asignar la mora a este registro. Por favor contacta con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public boolean finishLoan(int loanId) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "UPDATE loan SET loanStateId = 4 WHERE loanId = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, loanId);
            
            System.out.println(pst.toString());
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al finalizar el prestamo. Por favor contacta con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public int getLatestLoan() {
        int response = -1;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM loan ORDER BY loanId DESC LIMIT 1;";
            
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                response = rs.getInt("loanId");
            }
            
            cn.close();
            st.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al crear el prestamo. Por favor contacta con el administrador.");
        }
        
        return response;
    }
    
    public boolean addItemToLoan(int loanId, int itemId) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "INSERT INTO borrowedmaterial(loanId, inventoryMaterialId) VALUES (?,?)";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, loanId);
            pst.setInt(2, itemId);
            
            if (!pst.execute()) response = true;
            
            cn.close();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un error al crear el prestamo. Por favor contacta con el administrador.");
        }
        
        return response;
    }
}