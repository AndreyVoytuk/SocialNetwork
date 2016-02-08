package JdbcDatasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
///*
public class DatasourceConnection {
    Connection      connection = null;
    BasicDataSource bdSource   = new BasicDataSource();

    public DatasourceConnection()
    {
        bdSource.setDriverClassName("org.postgresql.Driver");
        bdSource.setUrl("jdbc:postgresql://localhost:5432/SocialNetwork");
        bdSource.setUsername("postgres");
        bdSource.setPassword("Hameleon123");
    }

    public Connection getConnection()
    {
        Connection con = null;
        try
        {
            if( connection != null )
            {
                System.out.println("Cant create a New Connection");
            }
            else
            {
                con = bdSource.getConnection();
            }
        }
        catch( Exception e )
        {
            System.out.println("Error Occured " + e.toString());
        }
        return con;
    }
//    private DataSource dsConnection;
//
//    public DatasourceConnection(){
//        try {
//            Context context = new InitialContext();
//            dsConnection = (DataSource) context.lookup("java:comp/env/jdbc/SocialNetwork");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = dsConnection.getConnection ();
//        } catch (SQLException e) {
//            e.printStackTrace ();
//        }
//        return connection;
//    }
//    private InitialContext initContext;
//
//    public DatasourceConnection() {
//        try {
//            initContext = new InitialContext();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Connection getConnection() {
//        try {
//            Context context =(Context) initContext.lookup("jdbc:comp/env");
//            DataSource dataSource = (DataSource) context.lookup("jdbc/SocialNetwork");
//            return dataSource.getConnection();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closePreparedStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
