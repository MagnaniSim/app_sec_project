package mainlibrary;

import java.sql.*;
import javax.swing.JTextField;

public class TransBookDao {

    public static boolean checkBook(String bookcallno) {
        boolean status = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select * from Books where BookID=?");
            ps.setString(1, bookcallno);
            rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static boolean BookValidate(Integer BookID) {
        boolean status = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select * from Books where BookID = ?");
            ps.setString(1, BookID.toString());
            rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static boolean UserValidate(Integer UserID) {
        boolean status = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select * from Users where UserID = ?");
            ps.setString(1, UserID.toString());
            rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static int updatebook(String bookcallno) {
        int status = 0;
        int quantity = 0, issued = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select quantity,issued from books where callno=?");
            ps.setString(1, bookcallno);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
                issued = rs.getInt("issued");
            }

            if (quantity > 0) {
                PreparedStatement ps2 = con.prepareStatement("update books set quantity=?,issued=? where callno=?");
                ps2.setInt(1, quantity - 1);
                ps2.setInt(2, issued + 1);
                ps2.setString(3, bookcallno);

                status = ps2.executeUpdate();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static int IssueBook(int BookID, int UserID, String IDate, String RDate) {
        int status = 0;
        PreparedStatement ps = null;
        
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("insert into IssuedBook values(?,?,?,?)");
            ps.setInt(1, BookID);
            ps.setInt(2, UserID);
            ps.setString(3, IDate);
            ps.setString(4, RDate);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            status = 0;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static int ReturnBook(int BookID, int UserID) {
        int status = 0;
        PreparedStatement ps = null;
        
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("delete from IssuedBook where BookID=? and UserID=?");
            ps.setInt(1, BookID);
            ps.setInt(2, UserID);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static boolean CheckIssuedBook(int BookID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select * from IssuedBook  where BookID=?");
            ps.setInt(1, BookID);
            rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        return status;
    }

    public static int Check(int UserID) {
        boolean status = false;
        int num = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection con = DB.getConnection()) {
            ps = con.prepareStatement("select * from Book_Count where UserID=?");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            status = rs.next();
            num = rs.getInt("BookNo");
            con.close();
        } catch (Exception e) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
        if ((num == 5 || !status)) {
            return 0;
        } else {
            return 1;
        }
    }

}
