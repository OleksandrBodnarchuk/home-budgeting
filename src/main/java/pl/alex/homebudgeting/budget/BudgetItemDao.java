package pl.alex.homebudgeting.budget;

import pl.alex.homebudgeting.db.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BudgetItemDao {
    private DataSource dataSource;

    public BudgetItemDao() {
        try {
            this.dataSource= DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(BudgetItem item){
        final String sqlStatement = "INSERT INTO budget_item (description,value,type) VALUES (?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setString(1,item.getDescription());
            preparedStatement.setBigDecimal(2,item.getValue());
            preparedStatement.setString(3,item.getType().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
