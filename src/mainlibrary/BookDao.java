package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
public static int save(String callno,String name,String author,String publisher,int quantity){
    PreparedStatement ps = null;
	int status=0;
	try (Connection con = DB.getConnection()) {
		ps=con.prepareStatement("insert into books(callno,name,author,publisher,quantity) values(?,?,?,?,?)");
		ps.setString(1,callno);
		ps.setString(2,name);
		ps.setString(3,author);
		ps.setString(4,publisher);
		ps.setInt(5,quantity);
		status=ps.executeUpdate();
		con.close();
	}catch(Exception e){
        System.out.println(e);
    } finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
	return status;
}

    /**
     *
     * @param Publisher
     * @return
     */
    public static boolean PublisherValidate( String Publisher)
{
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean status = false;
    try(Connection con = DB.getConnection()) {
        ps = con.prepareStatement("select * from Publisher where PublisherName = ?"); 
        ps.setString(1, Publisher);
        rs=ps.executeQuery();
        status=rs.next();
        con.close();
    }catch(Exception e){
		System.out.println(e);
	} finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    return status;
}
    
    public static int AddPublisher( String Publisher)
    {
        PreparedStatement ps = null;
        int status= 0;
        try(Connection con = DB.getConnection()) {
		ps=con.prepareStatement("insert into Publisher(PublisherName) values(?)");
		ps.setString(1,Publisher);
		status=ps.executeUpdate();
                con.close();
	}catch(Exception e){
		System.out.println(e);
	} finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
	return status;
        
    }

  
    public static int SaveBook(String BookN, String AuthorN, String PublisherN, String ShelfN, String RowN, String GenreN) {
        PreparedStatement ps = null;        
		int status= 0;
        try(Connection con = DB.getConnection()) {
                // This query fails with 
                // java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds 
                // to your MySQL server version for the right syntax to use near 'Row) values( ...
                // Not sure about why??
		ps=con.prepareStatement("insert into Books(BookName,Author,Genre,Publisher,Shelf,Row) values(?,?,?,?,?,?)");
		ps.setString(1,BookN);
                ps.setString(2, AuthorN);
                ps.setString(3, GenreN);
                ps.setString(4, PublisherN);
                ps.setString(5, ShelfN);
                ps.setString(6, RowN);
		status=ps.executeUpdate();
                con.close();
	}catch(Exception e){
		System.out.println(e);
	} finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
	return status;    }
    
    public static int Delete(int BookID)
    {
		PreparedStatement ps = null;
        int status= 0;
        try(Connection con = DB.getConnection()) {
		ps=con.prepareStatement("DELETE FROM Books where BookID=?");
		ps.setInt(1,BookID);
		status=ps.executeUpdate();
                con.close();
	}catch(Exception e){
		System.out.println(e);
	} finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
	return status;
        
    }
            
    
 

}
