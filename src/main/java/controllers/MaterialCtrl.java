package controllers;

import helpers.DatabaseConnection;
import helpers.GenerateCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Material;
import models.MaterialType;

public class MaterialCtrl {
   public MaterialCtrl() {}
   
   public List<Material> getMaterials() {
   List<Material> materials = new ArrayList<>();
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT code, title, mt.materialTypeName FROM material m INNER JOIN materialType mt ON m.materialTypeId = mt.materialTypeId;";
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Material material = new Material();
                
                material.setCode(rs.getString("code"));
                material.setTitle(rs.getString("title"));
                material.setMaterialTypeName(rs.getString("materialTypeName"));
                
                materials.add(material);
            }
            
            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los materiales. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return materials;
   }
   
   public Material getMaterial(String code) {
        Material material = null;
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM material WHERE code = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, code);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                material = new Material();
                
                material.setCode(rs.getString("code"));
                material.setTitle(rs.getString("title"));
                material.setDescription(rs.getString("description"));
                material.setDuration(rs.getString("duration"));
                material.setSongNumber(rs.getInt("songNumber"));
                material.setPublishDate(rs.getString("publishDate"));
                material.setMaterialTypeId(rs.getInt("materialTypeId"));
                material.setCreatorId(rs.getInt("creatorId"));
                material.setEditorialId(rs.getInt("editorialId"));
            }
            
            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener el material. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return material;
   }
   
   public List<MaterialType> getMaterialTypes() {
        List<MaterialType> materialTypes = new ArrayList<>();
        
        try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "SELECT*FROM materialType";
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                MaterialType materialType = new MaterialType();
                
                materialType.setMaterialTypeId(rs.getInt("materialTypeId"));
                materialType.setMaterialTypeName(rs.getString("materialTypeName"));
                
                materialTypes.add(materialType);
            }
            
            cn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al obtener los tipos de materiales. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
        
        return materialTypes;
   }
   
   public boolean createMaterial(Material material) {
       boolean response = false;
       
       String code = new GenerateCode(material.getMaterialTypeId()).getCode();
       material.setCode(code);
       
       try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            PreparedStatement pst = generatePreparedStatement(material, cn);
            
            if(!pst.execute()) response = true;
    
            pst.close();
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al crear el material. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
       
       return response;
   }
   
   public boolean deleteMaterial(String code) {
       boolean response = false;
       
       try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "DELETE FROM material WHERE code = ?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1, code);
            
            if(!pst.execute()) response = true;
    
            pst.close();
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar el material. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
       
       return response;
   }
   
   public boolean updateMaterial(Material material) {
       boolean response = false;
       
       try {
            DatabaseConnection dbcn = new DatabaseConnection();
            Connection cn = dbcn.getConnection();
            
            String sql = "UPDATE material SET title = ?, description = ?, duration = ?, songNumber = ?, publishDate = ?, creatorId = IF(? = 0, NULL, ?), editorialId = IF(? = 0, NULL, ?) WHERE code = ?";

            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, material.getTitle());
            pst.setString(2, material.getDescription());
            pst.setString(3, material.getDuration());
            pst.setInt(4, material.getSongNumber());
            pst.setString(5, material.getPublishDate());
            pst.setInt(6, material.getCreatorId());
            pst.setInt(7, material.getCreatorId());
            pst.setInt(8, material.getEditorialId());
            pst.setInt(9, material.getEditorialId());
            pst.setString(10, material.getCode());

           System.out.println(pst.toString());
            
            if(!pst.execute()) response = true;
    
            pst.close();
            cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar el material. Por favor contacte con el administrador.");
            System.out.println(e.getMessage());
        }
       
       return response;
   }
   
   private PreparedStatement generatePreparedStatement(Material material, Connection cn) throws SQLException {
       String sql = "";
       switch(material.getMaterialTypeId()) {
           case 1:
               sql = "INSERT INTO material(code, title, description, publishDate, materialTypeId, creatorId, editorialId) VALUES (?,?,?,?,?,?,?)";
               PreparedStatement pstBook = cn.prepareStatement(sql);
               
               pstBook.setString(1, material.getCode());
               pstBook.setString(2, material.getTitle());
               pstBook.setString(3, material.getDescription());
               pstBook.setString(4, material.getPublishDate());
               pstBook.setInt(5, material.getMaterialTypeId());
               pstBook.setInt(6, material.getCreatorId());
               pstBook.setInt(7, material.getEditorialId());
               
               return pstBook;
               
            case 2:
               sql = "INSERT INTO material(code, title, description, publishDate, materialTypeId, creatorId, editorialId) VALUES (?,?,?,?,?,?,?)";
               PreparedStatement pstSecondBook = cn.prepareStatement(sql);
               
               pstSecondBook.setString(1, material.getCode());
               pstSecondBook.setString(2, material.getTitle());
               pstSecondBook.setString(3, material.getDescription());
               pstSecondBook.setString(4, material.getPublishDate());
               pstSecondBook.setInt(5, material.getMaterialTypeId());
               pstSecondBook.setInt(6, material.getCreatorId());
               pstSecondBook.setInt(7, material.getEditorialId());
               
               return pstSecondBook;
               
             case 3:
               sql = "INSERT INTO material(code, title, description, publishDate, materialTypeId, editorialId) VALUES (?,?,?,?,?,?)";
               PreparedStatement pstMagazine = cn.prepareStatement(sql);
               
               pstMagazine.setString(1, material.getCode());
               pstMagazine.setString(2, material.getTitle());
               pstMagazine.setString(3, material.getDescription());
               pstMagazine.setString(4, material.getPublishDate());
               pstMagazine.setInt(5, material.getMaterialTypeId());
               pstMagazine.setInt(6, material.getEditorialId());
               
               return pstMagazine;
               
            case 4:
               sql = "INSERT INTO material(code, title, description, materialTypeId, duration, songNumber) VALUES (?,?,?,?,?,?)";
               PreparedStatement pstCD = cn.prepareStatement(sql);
               
                
               
               pstCD.setString(1, material.getCode());
               pstCD.setString(2, material.getTitle());
               pstCD.setString(3, material.getDescription());
               pstCD.setInt(4, material.getMaterialTypeId());
               pstCD.setString(5, material.getDuration());
               pstCD.setInt(6, material.getSongNumber());
               
               System.out.println(pstCD.toString());
               
               return pstCD;
           default:
               sql = "INSERT INTO material(code, title, description, materialTypeId) VALUES (?,?,?,?)";
               PreparedStatement pst = cn.prepareStatement(sql);
               
               pst.setString(1, material.getCode());
               pst.setString(2, material.getTitle());
               pst.setString(3, material.getDescription());
               pst.setInt(4, material.getMaterialTypeId());
               
               return pst;
       }
   }
}
