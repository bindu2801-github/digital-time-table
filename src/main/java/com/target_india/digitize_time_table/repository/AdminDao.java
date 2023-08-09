package com.target_india.digitize_time_table.repository;
import java.sql.*;


import com.target_india.digitize_time_table.config.DbConnection;
import com.target_india.digitize_time_table.controller.AdminController;
import com.target_india.digitize_time_table.model.Instructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDao{

    private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);
    Connection connection;



    DbConnection dbConnection;

    AdminDao(){
        dbConnection = new DbConnection();
        connection = dbConnection.getDbConnection();
        logger.info("Opened database successfully");
    }

    AdminDao(Connection connection){
        this.connection = connection;
    }

    public ResultSet findCourseByClassId(int classId){
        ResultSet resultSet=null;
        try {
            String query = "select course_table.course_id,course_table.course_name,instructor_table.instructor_name " +
                    "from course_table " +
                    "inner join instructor_table on course_table.instructor_id = instructor_table.instructor_id " +
                    "where course_table.class_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (SQLException exception) {
            logger.error(String.valueOf(exception));
        }
        return resultSet;
    }

    public ResultSet findClassInfo(int classId){
        ResultSet resultSet=null;
        try {
            String query = "select * from class_table where class_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, classId);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (SQLException exception) {
            logger.error(String.valueOf(exception));
        }
        return resultSet;
    }

    public ResultSet findAllClassInfo(){
        ResultSet resultSet=null;
        try {
            String query = "select * from class_table ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (SQLException exception) {
            logger.error(String.valueOf(exception));
        }
        return resultSet;
    }

    public ResultSet findAllAdmins() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM admin_table";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException exception) {
            logger.error(String.valueOf(exception));
        }
        return resultSet;
    }

    public ResultSet findAdminById(int id) {
        ResultSet resultSet = null;
        try {
            String query = "select * from admin_table where admin_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        }catch (SQLException exception) {
            logger.error(String.valueOf(exception));
        }
        return resultSet;
    }
}