package helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class GenerateCode {
    private String code;
    private ArrayList<Integer> allowedTypes = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
    
    public String getCode() {
        return code;
    }
    
    public GenerateCode(int type){
        if(!allowedTypes.contains(type)) {
            code = "invalid";
            return;
        }
        
        String latestCode = getLatestCode(type);
        code = generateCode(latestCode);
    }
    
    private String generateCode(String latestCode) {
        String prefix = latestCode.substring(0, 3);
        String numericPart = latestCode.substring(3);
        
        int newCode = Integer.parseInt(numericPart) + 1;
        
        String newNumericPart = String.format("%05d", newCode);
        
        return prefix + newNumericPart;
    }
    
    private String getLatestCode(int typeId) {
        DatabaseConnection dbcn = new DatabaseConnection();
        Connection cn = dbcn.getConnection();
        String code = "";
        
        try {
            
            String sql = "SELECT code FROM material WHERE materialTypeId = ? ORDER BY code DESC LIMIT 1";
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setInt(1, typeId);
            ResultSet rs = pst.executeQuery();
           
            while (rs.next()) {                
                code = rs.getString("code");
            }
            
            cn.close();
            pst.close();
            rs.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedio un problema al obtener el ultimo código disponible. Contacta con el administrador");
        }
        
        if (code.length() == 0) {
            if (typeId == 1) code = "LIB00000";
            if (typeId == 2) code = "OBR00000";
            if (typeId == 3) code = "REV00000";
            if (typeId == 4) code = "CDA00000";
            if (typeId == 5) code = "TES00000";
            if (typeId == 5) code = "TES00000";
            if (typeId == 6) code = "OTR00000";
        }
        
        return code;
    }
}
