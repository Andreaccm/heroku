package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.loginB;
import controllers.perfiluser;

public class DB {
	Connection connection = null;
	Statement stmt = null;
	ResultSet result;

	
	public void register(String name, String user,String email, String phone,String date,String pwd) {
		try {
			Class.forName(DBconnect.drive);
			connection = DriverManager.getConnection(DBconnect.url, DBconnect.usr, DBconnect.pwd1);
			stmt = connection.createStatement();
			if(connection != null) {
				System.out.println("Connected to database");
			}
			String sql = "INSERT INTO account values ('"+name+"','"+user+"','"+email+"','"+phone+"','"+date+"','"+pwd+"')";
			result = stmt.executeQuery(sql);
			connection.close();
			stmt.close();
			
		}catch (ClassNotFoundException | SQLException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
	}
	
	
	public boolean login(loginB lb) throws ClassNotFoundException{//read
		boolean status = false;
		Class.forName(DBconnect.drive);
		
		try (
			Connection connection = DriverManager.getConnection(DBconnect.url, DBconnect.usr, DBconnect.pwd1);		
			PreparedStatement pstmt = connection.prepareStatement("SELECT *FROM account where email = ? and pws = ?")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
				System.out.println(loginB.email+"  "+loginB.pwd);
				pstmt.setString(1, lb.getEmail());
				pstmt.setString(2, lb.getPwd());
				
				result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    printSQLException(ex);
		}
		return status;
	}
	
	public boolean perfil(perfiluser p,loginB lb) throws ClassNotFoundException {
		Class.forName(DBconnect.drive);
		boolean status = false;
		try (
				Connection connection = DriverManager.getConnection(DBconnect.url, DBconnect.usr, DBconnect.pwd1);		
				PreparedStatement pstmt = connection.prepareStatement("SELECT *FROM account WHERE email = ?")){
				
				if(connection!=null) {
					System.out.println("Conexion a la base de datos");
				}
				pstmt.setString(1, loginB.email);
				result = pstmt.executeQuery();
				
				while(result.next()) {
					perfiluser.name = result.getString(1);
					perfiluser.user = result.getString(2);
					perfiluser.email= result.getString(3);
					perfiluser.phone = result.getString(4);
					perfiluser.date= result.getString(5);
				}
				
				status = result.next();
				connection.close();
				
			}catch (SQLException ex) {
			    printSQLException(ex);
			}
		return status;
	}

	public boolean update(perfiluser p,loginB lb) throws ClassNotFoundException{
		boolean status = false;
		Class.forName(DBconnect.drive);
		try (
			Connection connection = DriverManager.getConnection(DBconnect.url, DBconnect.usr, DBconnect.pwd1);		
			PreparedStatement pstmt = connection.prepareStatement("UPDATE account SET full_name = ?, phone=?, pws = ? WHERE email =? ")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
			DB db =new DB();
			db.perfil(p, lb);
			System.out.println();
			pstmt.setString(1, perfiluser.name);
			pstmt.setString(2, perfiluser.phone);
			pstmt.setString(3,perfiluser.pwd);
			pstmt.setString(4,perfiluser.email);
			result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    printSQLException(ex);
		}
		return status;
	}
	public boolean delete(loginB lb) throws ClassNotFoundException{
		boolean status = false;
		Class.forName(DBconnect.drive);
		try (
			Connection connection = DriverManager.getConnection(DBconnect.url, DBconnect.usr, DBconnect.pwd1);		
			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM account where email=?")){
			
			if(connection!=null) {
				System.out.println("Conexion a la base de datos");
			}
			System.out.println(lb.email);
			pstmt.setString(1, lb.getEmail());
				result = pstmt.executeQuery();
				status = result.next();
				System.out.println(status);
			connection.close();
			
		}catch (SQLException ex) {
		    printSQLException(ex);
		}
		return status;
	}
	
	
	
	
	
	
	
	
	
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
