package native_jdbc_hikaricp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_hikaricp.dto.Student;

public class StudentDaoImpl implements StudentDao {
	private static final StudentDaoImpl instance = new StudentDaoImpl();
	
	
	public StudentDaoImpl() {

	}

	public static StudentDaoImpl getInstance() {
		return instance;
	}


	@Override
	public List<Student> selectStudentByAll(Connection con) throws SQLException {
		String sql = "select sno, sname, kor, eng, math from student";
		List<Student> list = new  ArrayList<Student>();
		try (PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					list.add(getStudent(rs));
				}
			}
		return list;
	}

	private Student getStudent(ResultSet rs) throws SQLException {
		int sno = rs.getInt("sno");
		String sname = rs.getString("sname");
		int kor = rs.getInt("kor");
		int eng = rs.getInt("eng");
		int math = rs.getInt("math");
		return new Student(sno, sname, kor, eng, math);
	}

}
