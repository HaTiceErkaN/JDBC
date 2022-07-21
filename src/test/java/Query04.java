import java.sql.*;

public class Query04 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/okul?serverTimezone=UTC","root","5131");
        //Statement st = con.createStatement();
        PreparedStatement ps = con.prepareStatement("select * from ogrenciler");
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println("1. Sutun Ismi = " + rsmd.getColumnName(1));
        System.out.println("2. Sutun Ismi = " + rsmd.getColumnName(2));
        System.out.println("3. Sutun Ismi = " + rsmd.getColumnName(3));
        System.out.println("4. Sutun Ismi = " + rsmd.getColumnName(4));
        System.out.println("==============================================");

        System.out.println("Toplam Sutun Sayisi = " + rsmd.getColumnCount());

        System.out.println("==============================================");

        System.out.println("1. Sutunun Data Tipi = " + rsmd.getColumnTypeName(1));
        System.out.println("2. Sutunun Data Tipi = " + rsmd.getColumnTypeName(2));
        System.out.println("3. Sutunun Data Tipi = " + rsmd.getColumnTypeName(3));
        System.out.println("4. Sutunun Data Tipi = " + rsmd.getColumnTypeName(4));

        System.out.println("==============================================");

        System.out.println("Tablonun Ismi = " + rsmd.getTableName(1));

        System.err.println("==============================================");

        while (rs.next()){
            System.out.printf("%-6d %-15.15s %-8s %-8s\n", rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4));
        }


    }
}
