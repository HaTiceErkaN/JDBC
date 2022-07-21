public class Query10 extends DatabaseUtility{
    public static void main(String[] args) {

        createConnection();

        String query = "select * from ogrenciler";
        System.out.println("getColumnNames(query) = " + getColumnNames(query));

        System.out.println(getColumnData(query, "id"));
        System.out.println(getColumnData(query, "isim"));
        System.out.println(getColumnData(query, "veli_isim"));
        System.out.println(getColumnData(query, "yazili_notu"));
    }
}
