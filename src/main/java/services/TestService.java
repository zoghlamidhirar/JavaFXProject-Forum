package services;

import models.Test;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestService implements IService<Test> {
    private Connection connection;

    public TestService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Test test) throws SQLException {
        String sql = "insert into test (name,age) VALUES ('"+test.getName() +"',"+ test.getAge()+")";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void update(Test test) throws SQLException {
        String sql = "update test set name = ?,  age = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2, test.getName());
        preparedStatement.setInt(3, test.getAge());
        preparedStatement.setInt(1, test.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from test where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Test> getAll() throws SQLException {
        String sql = "select * from test";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Test> tests = new ArrayList<>();
        while (rs.next()) {
            Test u = new Test();
            u.setId(rs.getInt("id"));
            u.setAge(rs.getInt("age"));
            u.setName(rs.getString("name"));

            tests.add(u);
        }
        return tests;
    }

    @Override
    public Test getById(int idUser) throws SQLException {
        String sql = "SELECT `name`, `age` FROM `test` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idUser);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");

            return new Test(idUser, age, name);
        } else {
            // Handle the case when no matching record is found
            return null;
        }

    }
}
