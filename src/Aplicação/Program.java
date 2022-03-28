package Aplicação;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			conn = DB.getConnection();
//			st = conn.createStatement();
//			
//			rs = st.executeQuery("select * from department");
//			
//			while (rs.next()) {
//				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));	
//			}
//			
//		} catch ( SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DB.closeResultSett(rs);
//			DB.closeStatement(st);
//			DB.closeConnection();
//		}
		SimpleDateFormat sdf= new SimpleDateFormat("dd/mm/yyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO seller"
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES"
					+"(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			st.setString(1, "xcarl purple");
			st.setString(2, "ddandre1010@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
			st.setDouble(4, 3000.00);	
			st.setInt(5, 4);
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected>0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done!! Id = " +id);
					
				}
			} else {
                 System.out.println("nenhuma linha alterada");
			}
		} catch (SQLException e){
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
			
		
		
		
	}

}
