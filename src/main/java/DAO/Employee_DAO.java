package DAO;

import Database.JDBC_Util;
import Model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class Employee_DAO implements DAO_Interface<Employee, String> {


    @Override
    public int insert(Employee entity) {
        String sql = "INSERT INTO employees (employee_id, Name, phone_number, Address, Email) VALUES (?,?,?,?,?)";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getEmployee_id());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getPhone_number());
            statement.setString(4, entity.getAddress());
            statement.setString(5, entity.getEmail());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int update(Employee entity) {
        String sql = "UPDATE employees SET Name = ?, phone_number = ?, Address = ?, Email = ? WHERE employee_id = ?";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPhone_number());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getEmail());
            statement.setInt(5, entity.getEmployee_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Employee entity) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getEmployee_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Employee> findAll() {
        ArrayList<Employee> listEmployee = new ArrayList<Employee>();
        String sql = "SELECT * FROM employees";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("Name"));
                employee.setPhone_number(resultSet.getString("phone_number"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setEmail(resultSet.getString("Email"));

                listEmployee.add(employee);
            }
            JDBC_Util.closeConnection(connection);
            return listEmployee;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findById(String s) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("Name"));
                employee.setPhone_number(resultSet.getString("phone_number"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setEmail(resultSet.getString("Email"));
            }
            JDBC_Util.closeConnection(connection);
            return employee;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Employee> findByCondition(String condition) {
        ArrayList<Employee> listEmployee = new ArrayList<Employee>();
        String sql = "SELECT * FROM employees WHERE " + condition;

        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("Name"));
                employee.setPhone_number(resultSet.getString("phone_number"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setEmail(resultSet.getString("Email"));

                listEmployee.add(employee);
            }
            JDBC_Util.closeConnection(connection);
            return listEmployee;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Employee_DAO getInstance(){
        return new Employee_DAO();
    }
}
