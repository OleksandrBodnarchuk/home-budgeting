package pl.alex.homebudgeting.budget;

import pl.alex.homebudgeting.db.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetItemDao {
    private final DataSource dataSource;

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

    public List<BudgetItem> findAllByType(BudgetItemType type){
        final String sql = "SELECT description, value,type FROM budget_item WHERE type=?";
        List<BudgetItem>items = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,type.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String description = resultSet.getString("description");
                BigDecimal value = resultSet.getBigDecimal("value");
                items.add(new BudgetItem(description,value,type));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return items;
    }
}
