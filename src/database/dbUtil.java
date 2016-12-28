package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class dbUtil {
	/**
	 * @author SONTD
	 * */
	
	private String url;
	private String user;
	private String password;
	private Connection connection;
	private Statement statement;

	// Tạo connection kết nối đến database
	public void initialize() {
		Properties config = new Properties();
		try {
			config.load(new FileInputStream("config.properties"));
			url = config.getProperty("url");
			user = config.getProperty("user");
			password = config.getProperty("pass");

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);

			System.out.println("Connect successful!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (connection == null) {
			throw new NullPointerException("Connection is null!");
		}
	}

	// Phương thức khởi tạo statement
	public void createStatement() {
		if (statement == null)
			try {
				statement = connection.createStatement();

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Phương thức thực thi câu lệnh truy vấn Select
	 * 
	 * @param sqlCommand
	 *            câu lệnh truy vấn
	 * */
	public ResultSet receiveData(String sqlCommand) {
		try {
			ResultSet resultSet = statement.executeQuery(sqlCommand);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Phương thức thực thi lệnh UPDATE
	/**
	 * Thực thi lệnh update
	 * 
	 * @param sqlCommand
	 *            Câu lệnh Sql
	 * @param indexes
	 *            mảng các chỉ số của ký tự '?'
	 * @param values
	 *            mảng giá trị truyền vào các dấu '?'
	 * */
	public int executeUpdate(String sqlCommand, int[] indexes,
			ArrayList<Object> values) {
		int rowsEffected = 0;
		try {
			PreparedStatement pStatement = connection
					.prepareStatement(sqlCommand);
			for (int i = 0; i < values.size(); i++) {
				pStatement.setObject(indexes[i], values.get(i));
			}
			rowsEffected = pStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsEffected;
	}

	public int executeInsert(String sqlCommand, int[] indexes,
			ArrayList<Object> values) {
		return executeUpdate(sqlCommand, indexes, values);
	}

	public int executeDelete(String sqlCommand, int[] indexes,
			ArrayList<Object> values) {
		return executeUpdate(sqlCommand, indexes, values);
	}

	// Phương thức đếm số bản ghi của một resultset
		/**
		 * Trả về số bản ghi của một resultset
		 * 
		 * @param resultSet
		 *            Tập các bản ghi cần đếm số dòng
		 * */
	public int getRowCount(ResultSet resultSet) {
		int count = 0;
		try {
			while(resultSet.next())
				count++;
			resultSet.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static void main(String[] args) {
		dbUtil myDB = new dbUtil();
		myDB.initialize();
		myDB.createStatement();
		
		// select from database and print to console
		ResultSet rs = myDB.receiveData("SELECT * FROM nhanvien");
		try {
			System.out.println(myDB.getRowCount(rs));
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		// update
		String sqlCommand = "UPDATE `tb1_sinhvien` SET `Ma So` = ?, `Ho Ten` = ?, `Ngay Sinh` = ? WHERE `Ma So` = ?";
		int[] indexes = new int[] { 1, 2, 3, 4 };
		ArrayList<Object> values = new ArrayList<Object>();
		values.add(100);
		values.add("Java SE");
		values.add(new MyDate().toDate("25/12/2020"));
		values.add(11);

		// insert
		indexes = new int[] { 1, 2, 3, 4, 5 };
		sqlCommand = "INSERT INTO `tb1_sinhvien` VALUES (?, ?, ?, ?, ?)";
		// Clear values array
		values.clear();
		values.add(11);
		values.add("Bruno Mars");
		values.add(new MyDate().toDate("30/01/2000"));
		values.add("California");
		values.add("Nam");
		myDB.executeInsert(sqlCommand, indexes, values);

		// delete
		sqlCommand = "DELETE FROM `tb1_sinhvien` WHERE `Ma So` = ?";
		indexes = new int[] { 1 };
		// Clear values array
		values.clear();
		values.add(100);
		myDB.executeUpdate(sqlCommand, indexes, values);

		// select from database and print to console
		ResultSet rs = myDB.receiveData("SELECT * FROM tb1_sinhvien");
		try {
			while (rs.next()) {
				System.out.println(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		 */
	}
}
