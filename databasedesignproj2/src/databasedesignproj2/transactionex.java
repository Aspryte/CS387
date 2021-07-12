package databasedesignproj2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class transactionex {
	
	public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException {

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","6855dua11y");

		if (conn != null)
		{
			System.out.println("Connection OK");
		}
		else
		{
			System.out.println("Connection Failed");
		}
		
		conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 
		
		Statement stmt = null;
		
		try {stmt = conn.createStatement();
		String instring = "DELETE FROM depot where dep_id = 'd4'";
		stmt.executeUpdate(instring);
		conn.commit();
		stmt.close();

		} catch (SQLException e) {
			System.out.println("catch Exception");
			// For atomicity
			conn.rollback();
			stmt.close();
			return;
		} // main
		//conn.commit();
		//stmt.close();
		conn.close();
	}
}


/*	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		
	try
	{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","6855dua11y");

		conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);


		if (conn != null)
		{
			System.out.println("Connection OK");
		}
		else
		{
			System.out.println("Connection Failed");
		}
		
		stmt = conn.createStatement();
		String instring = "DELETE FROM Depot where dep_id = 'd4'";
		stmt.executeUpdate(instring);
		stmt.close();
		
		
		
		
		conn.commit();
		conn.close();
	}
	catch (SQLException | ClassNotFoundException e)
	{
		System.out.println(e);
		conn.rollback();
		conn.commit();
		conn.close();
		return;
	}

	}
}*/
