package com.my.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.my.spring.pojo.UserDetails;

public class CSVDao {
	public ArrayList<UserDetails> getContent(String name){
    
        Connection conn = null;
        Statement stmt = null;
		try {
			// Load the driver.
			Class.forName("org.relique.jdbc.csv.CsvDriver");

			// conn = DriverManager.getConnection("jdbc:relique:csv:" +
			// "C:\\Users\\AMY\\Documents\\workspace-sts-3.8.3.RELEASE\\CSVWebApplication");

			// relative path of csv
			String path = this.getClass().getClassLoader().getResource("").getPath();
			System.out.println("path is : " + path);
			String strArray[] = path.split("spring-1.0.0-BUILD-SNAPSHOT");
			System.out.println("first arg" + strArray[0]);
			
			// Create a connection. The first command line parameter is
			// the directory containing the .csv files.
			conn = DriverManager.getConnection("jdbc:relique:csv:" + strArray[0]);

			// Create a Statement object to execute the query with.
			stmt = conn.createStatement();
		} catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex.getMessage());
        }
        ArrayList<UserDetails> list = new ArrayList<UserDetails>();
        String sqlQuery = "SELECT * FROM " + name;
        try {

            ResultSet rs = stmt.executeQuery(sqlQuery);
            int sum=0;
       
             while(rs.next()){  
            	 ++sum;
            	 UserDetails  userDetails = new UserDetails();
            	 userDetails.setId(rs.getInt("id"));
            	 userDetails.setFirstName(rs.getString("first_name"));
            	 userDetails.setLastName(rs.getString("last_name"));
            	 userDetails.setCompany(rs.getString("company"));
            	 userDetails.setEmail(rs.getString("email"));
            	 userDetails.setAddress(rs.getString("address1"));
            	 userDetails.setSecondaryAddress(rs.getString("address2"));
            	 userDetails.setZip(rs.getInt("zip"));
            	 userDetails.setCity(rs.getString("city"));
            	 userDetails.setStateLong(rs.getString("state_long"));
            	 userDetails.setState(rs.getString("state"));
            	 userDetails.setPhone(rs.getString("phone"));
                list.add(userDetails);                
            }
             System.out.println(sum);
            System.out.println(sqlQuery);

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException" + ex.getMessage());
            }
        }
        return list;
}
    
}
