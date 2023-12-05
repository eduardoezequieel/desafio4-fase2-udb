package controllers;

import helpers.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Inventory;
import models.Library;

public class InventoryCtrl {
    public InventoryCtrl() {}
    
    public List<Inventory> getInventory() {
        List<Inventory> inventory = new ArrayList<>();
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT inventoryId, m.code, m.title, mt.materialTypeName, l.libraryName FROM inventory i INNER JOIN material m ON m.code = i.materialCode INNER JOIN library l ON l.libraryId = i.libraryId INNER JOIN materialType mt ON mt.materialTypeId = m.materialTypeId;";
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Inventory item = new Inventory();
                
                item.setInventoryId(rs.getInt("inventoryId"));
                item.setMaterialCode(rs.getString("code"));
                item.setTitle(rs.getString("title"));
                item.setMaterialTypeName(rs.getString("materialTypeName"));
                item.setLibraryName(rs.getString("libraryName"));
                
                inventory.add(item);
            }
            
            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener el inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return inventory;
    }
    
    public Inventory getInventoryItem(int id) {
        Inventory item = null;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM inventory WHERE inventoryId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                item = new Inventory();
                
                item.setInventoryId(rs.getInt("inventoryId"));
                item.setMaterialCode(rs.getString("materialCode"));
                item.setLibraryId(rs.getInt("libraryId"));
                item.setIsbn(rs.getString("isbn"));
                item.setIssn(rs.getString("issn"));
                item.setNotes(rs.getString("notes"));
            }
            
            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener el elemento del inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return item;
    }
    
    public List<Library> getLibraries() {
        List<Library> libraries = new ArrayList<>();
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM library";
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Library library = new Library();
                
                library.setLibraryId(rs.getInt("libraryId"));
                library.setLibraryName(rs.getString("libraryName"));
                
                libraries.add(library);
            }
            
            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener el inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return libraries;
    }
    
    public boolean deleteInventory(int id) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "DELETE FROM inventory WHERE inventoryId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar el elemento del inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public boolean addItemToInventory(Inventory inventory) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "INSERT INTO inventory(materialCode, libraryId, isbn, issn, notes) VALUES(?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, inventory.getMaterialCode());
            pst.setInt(2, inventory.getLibraryId());
            pst.setString(3, inventory.getIsbn());
            pst.setString(4, inventory.getIssn());
            pst.setString(5, inventory.getNotes());
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al agregar el elemento al inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
    public boolean updateItem(Inventory inventory) {
        boolean response = false;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "UPDATE inventory SET libraryId = ?, isbn = ?, issn = ?, notes = ? WHERE inventoryId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, inventory.getLibraryId());
            pst.setString(2, inventory.getIsbn());
            pst.setString(3, inventory.getIssn());
            pst.setString(4, inventory.getNotes());
            pst.setInt(5, inventory.getInventoryId());
            
            System.out.println(pst.toString());
            
            if(!pst.execute()) response = true;
            
            cn.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar el elemento del inventario. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return response;
    }
    
}
