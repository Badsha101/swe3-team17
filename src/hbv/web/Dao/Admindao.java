package hbv.example.Dao;

import hbv.example.Module.Admin;
import hbv.example.database.DBUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Admindao {
    public Admin isValidAdmin(String email, String password) {
        String Query="Select * from admin where Email = ? and password = ?";

       Admin admin= null;

      try(Connection conn=DBUtil.getConnection();
          PreparedStatement preparedStatement= conn.prepareStatement(Query);
      ){

              preparedStatement.setString(1, email);
              preparedStatement.setString(2, password);

              try (ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {

                      admin = new Admin();
                      admin.setId(resultSet.getInt("id"));
                      admin.setEmail(resultSet.getString("email"));
                      admin.setPassword(resultSet.getString("password"));
                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }

          return admin;
      }


    }
