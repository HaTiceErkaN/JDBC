import java.sql.*;

public class Query03 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC","root","5131");
        Statement st = con.createStatement();

        // SORU1: Bölümler tablosundan tüm kayıtları listeleyiniz.
        ResultSet rs = st.executeQuery("select * from bolumler");

        while (rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+ "\t" +rs.getString(3));
        }

        System.out.println("==================================================================================");
        // SORU2:SATIS ve MUHASEBE bolumlerinde calişan personelin isimlerini ve maaşlarını,
        // maaş ters sıralı listeleyiniz.
        ResultSet rs2 = st.executeQuery("select personel_isim, maas from personel" +
                " where bolum_id in(10,30)" +
                " order by maas desc");
        while (rs2.next()){
            System.out.println(rs2.getString(1)+"\t"+rs2.getInt(2));
        }

        System.out.println("==================================================================================");
        // Soru3: Tüm bölümlerde çalışan personel isimlerini, bölüm isimlerini ve maaşlarını,
        // bölüm ve maas sıralı listeleyiniz.
        // NOT: Çalışanı olamasa bile bölüm ismi gösterilmelidir.
        ResultSet rs3 = st.executeQuery("select personel.personel_isim, bolumler.bolum_isim, personel.maas from personel " +
                "right join bolumler " +
                "on personel.bolum_id = bolumler.bolum_id " +
                "order by bolumler.bolum_isim, personel.maas");

        while (rs3.next()){
            System.out.println(rs3.getString(1) + "\t" + rs3.getString(2) + "\t" + rs3.getInt(3));
        }
        System.out.println("==================================================================================");

        // SORU4: Maaşı en yüksek 10 kişinin bolümünü, adını ve maaşını listeleyiniz.
        ResultSet rs4 = st.executeQuery("select bolumler.bolum_isim,personel.personel_isim,personel.maas " +
                "from bolumler left join personel on personel.bolum_id=bolumler.bolum_id order by maas desc limit 10");
        while(rs4.next()){
            System.out.printf("%-16s %-16s %-8s\n", rs4.getString(2), rs4.getString(1), rs4.getInt(3));
        }


    }
}
