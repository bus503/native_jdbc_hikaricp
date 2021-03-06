package native_jdbc_hikaricp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import native_jdbc_hikaricp.LogUtil;
import native_jdbc_hikaricp.dao.DepartmentDao;
import native_jdbc_hikaricp.dto.Department;

public class DepartmentDaoImpl implements DepartmentDao {
	// singleton pattern

	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();

	private DepartmentDaoImpl() {}

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	@Override
	public List<Department> selectDepartmentByAll(Connection con) throws SQLException {
		String sql = "select deptno, deptname, floor from department";
		List<Department> list = new ArrayList<Department>();
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Connection con, Department department) {
		String sql = "insert into department values(?, ?, ?)";
		int res = -1;
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return res;
	}

	@Override
	public int updateDepartment(Connection con, Department department) throws SQLException {
		String sql = "update department set deptname=?, floor = ? where deptno = ?";
		int res = -1;
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setInt(3, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteDepartment(Connection con, Department department) throws SQLException {
		String sql = "delete from department where deptno = ?";
		int res = -1;
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Department selectDepartmentByNo(Connection con, int dno) throws SQLException {
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1,dno);
			LogUtil.prnLog(pstmt);
			try(ResultSet rs= pstmt.executeQuery()){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		}
		return null;
	}
}
