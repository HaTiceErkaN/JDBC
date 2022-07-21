import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query08 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "5131");

        //Statement st = con.createStatement();

        // SORU1: Urunler adında bir tablo olusturalım (id int, isim varchar(10), fiyat int)
        //st.execute("create table urunler (id int, isim char(20), fiyat int)");

        // SORU2: urunler tablosuna aşağıdaki verileri toplu bir şekilde etkileyin
        // Çok miktarda kayıt eklemek için PreparedStatement metodu kullanılır.
        // 1) Veri girişine uygun bir POJO (Plain Old Java Object) class oluşturulur
        // 2) POJO class ta nesneleri saklayacak bir collection oluşturulur
        // 3) Bir döngğü ile kayıtlar eklenir.

        List<Urun> kayitlar = new ArrayList<>();
        kayitlar.add(new Urun(101, "Laptop", 6500));
        kayitlar.add(new Urun(102, "PC", 4500));
        kayitlar.add(new Urun(103, "Telefon", 3500));
        kayitlar.add(new Urun(104, "Anakart", 1500));
        kayitlar.add(new Urun(105, "Klavye", 200));
        kayitlar.add(new Urun(106, "Mause", 100));

        PreparedStatement tablo = con.prepareStatement("insert into urunler values(?, ?, ?)");
        for (Urun w:kayitlar) {
            tablo.setInt(1,w.getId());
            tablo.setString(2,w.getIsim());
            tablo.setDouble(3,w.getFiyat());

            tablo.addBatch(); // Butun verileri bir araya getirir
        }
        tablo.executeBatch();
        System.out.println("Veriler Database'e eklendi");

        con.close();
        tablo.close();



    }

}
