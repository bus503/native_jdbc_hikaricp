package native_jdbc_hikaricp.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import native_jdbc_hikaricp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll(Connection con) throws SQLException;
	int insertDepartment(Connection con, Department department) throws SQLException;
	int updateDepartment(Connection con, Department department) throws SQLException;
	int deleteDepartment(Connection con, Department department) throws SQLException;
	Department selectDepartmentByNo(Connection con, int dno) throws SQLException;
}
