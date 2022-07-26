import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Query06 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "5131");

        Statement st = con.createStatement();

        // SORU1: Bölümler tablosunda yeni bir kayıt (80, 'ARGE', 'ISTANBUL')


       /* int s1=st.executeUpdate("insert into bolumler values (80, 'ARGE', 'ISTANBUL')");
        System.out.println(s1+ " Satir eklendi");

        */

        //executeUpdate etkilenen satir sayisini dondurur.

/*        // SORU2; Bölümler tablosuna birden fazla kayıt ekleyelim.
        String[] veri1 = {"insert into bolumler values (95, 'YEMEKHANEA', 'ISTANBUL')",
                "insert into bolumler values (85, 'OFIS1', 'Ankara')",
                "insert into bolumler values (75, 'OFIS2', 'TRABZON')"};
        int count = 0;
        for (String each : veri1) {
            count = count + st.executeUpdate(each);
        }
        System.out.println(count + " data eklendi");

 */

        //2.yol
        String[] veri2 = {"insert into bolumler values (81, 'YEMEKHANEA', 'ISTANBUL')",
                "insert into bolumler values (82, 'OFIS1', 'Ankara')",
                "insert into bolumler values (83, 'OFIS2', 'TRABZON')"};

        for (String each : veri2) {
            st.addBatch(each);  //Yukaridaki verilenlerin tamamini bir araya  topluyor
        }
        st.executeBatch();   // Bir araya getirilen verileri tek seferde gonderiyor
        System.out.println("Veriler database eklendi  ");


    }
}