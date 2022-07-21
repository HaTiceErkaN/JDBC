import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query09 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // SORU: urunler tablosuna aşağıdaki verileri toplu bir şekilde etkileyin
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "5131");

        List<Urun> kayitlar = new ArrayList<>();

        kayitlar.add(new Urun(200, "Asus", 7500));
        kayitlar.add(new Urun(201, "HP", 8500));
        kayitlar.add(new Urun(202, "Acer", 9500));
        kayitlar.add(new Urun(203, "Monster", 11500));
        kayitlar.add(new Urun(204, "Klavye", 1200));
        kayitlar.add(new Urun(205, "Fare", 1000));

        PreparedStatement tablo = con.prepareStatement("insert into urunler values(?, ?, ?)");
        for(Urun each:kayitlar){
            tablo.setInt(1, each.getId());
            tablo.setString(2, each.getIsim());
            tablo.setDouble(3, each.getFiyat());
            tablo.addBatch();
        }
        tablo.executeBatch();
        System.out.println("Veriler Database'e eklendi");

    }
}
