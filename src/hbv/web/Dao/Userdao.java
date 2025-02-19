package hbv.web.Dao;

import hbv.web.Module.User;
import hbv.web.db.DBUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

public class Userdao {

    public User isValidUser(String email, String password) {
        String query = "SELECT * FROM user WHERE Email = ? AND Password = ?";
        hbv.web.Module.User user = null;

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetch firstname and lastname from the database
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");

                    // Now create the User object with valid data
                    user = new User(email, password, firstname, lastname);
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstname"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPassword2(resultSet.getString("password2"));
                    System.out.println("Query is running");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean isEmail(String email) throws Exception {


        String query = "SELECT * FROM user WHERE Email = ?";


        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, email);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    public int addUser(User user) throws Exception {
        String query = "insert into user(firstname,lastname,email,password) values (?,?,?,?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());


            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return -1;


    }
}












