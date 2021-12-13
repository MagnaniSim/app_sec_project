package mainlibrary;

import java.sql.*;
import BCrypt.*;

public class LibrarianDao {

    public static int save(String name, String password, String email, String address, String city, String contact) {
        int status = 0;
        
        String salt = BCrypt.gensalt();
        String UserPassPepper = System.getProperty("pwdPepper") + password;
        String UserPassSaltPepper = BCrypt.hashpw(UserPassPepper, salt);    
        
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into librarian(name,password,email,address,city,contact) values(?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, UserPassSaltPepper);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, city);
            ps.setString(6, contact);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from Librarian where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static String validate(String name, String password) {
        String LibrarianPass = null;
        boolean status = false;
        ResultSet rs = null;

        String LibrarianPassPepper = System.getProperty("pwdPepper") + password;        
        
        try (Connection con = DB.getConnection()) {       
            PreparedStatement ps = con.prepareStatement("select * from Librarian where UserName=?");
            ps.setString(1, name);
            rs = ps.executeQuery();               
            status = rs.next();
            
            if (status) {
                String dbPassword = rs.getString("Password");

                String LibrarianPassSaltPepper = BCrypt.hashpw(LibrarianPassPepper, dbPassword);

                if (dbPassword != null && LibrarianPassSaltPepper != null && dbPassword.equals(LibrarianPassSaltPepper)) {
                    LibrarianPass = LibrarianPassSaltPepper;
                }
            }
            
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
  
        return LibrarianPass;
    }

}
