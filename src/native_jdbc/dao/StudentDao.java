package native_jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import native_jdbc_hikaricp.dto.Student;

public interface StudentDao {
	List<Student> selectStudentByAll(Connection con) throws SQLException;
}
