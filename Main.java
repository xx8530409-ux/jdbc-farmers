import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/farmers_market";
        String user = "root";
        String password = "Xz0977306612";

        System.out.println("正在連線資料庫 ... ");

    // 取得連線（自動載入 Driver）
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            if(!conn.isClosed()){
                System.out.println("連線成功！");
            }

            String sql = "SELECT product_id, product_name, product_size " + 
                         "FROM product WHERE product_category_id = ?" +
                         " AND product_name LIKE ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, 3); //佔位符?找ID，所以問號1，找ID=3
                ps.setString(2, "A%"); //佔位符?%找字串，所以問號2，找字串A%

                try (ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        System.out.println(rs.getInt("product_id") + " " + rs.getString("product_name") + " " + rs.getString("product_size"));
                    }
                }
            } 
 
        } catch (SQLException e) {
            System.out.println("資料庫連線失敗！!");
            e.printStackTrace();
        }
   }
}
