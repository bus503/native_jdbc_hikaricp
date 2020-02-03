package native_jdbc_hikaricp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;

import native_jdbc.dao.DepartmentDao;
import native_jdbc.dao.DepartmentDaoImpl;
import native_jdbc.dao.EmployeeDao;
import native_jdbc.dao.EmployeeDaoImpl;
import native_jdbc.ui.DlgEmployee;
import native_jdbc_hikaricp.ds.C3P0DataSource;
import native_jdbc_hikaricp.ds.DBCPDataSource;
import native_jdbc_hikaricp.ds.Hikari_DataSource;
import native_jdbc_hikaricp.ds.Hikari_DataSource2;
import native_jdbc_hikaricp.dto.Department;
import native_jdbc_hikaricp.dto.Employee;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		try (Connection con = Hikari_DataSource2.getConnection()){
//			소속부서사원검색테스트(con);
			
			DepartmentDao dao = DepartmentDaoImpl.getInstance();
			//추가할 부서정보
			Department newDept = new Department(6, "지원", 60);
//			dao.insertDepartment(con, newDept);
//			dao.updateDepartment(con, newDept);
			dao.deleteDepartment(con, newDept);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		}

	}

	private static void 소속부서사원검색테스트(Connection con) throws SQLException {
		EmployeeDao dao = EmployeeDaoImpl.getInstance();
		Department dept = new Department();
		dept.setDeptNo(2);
		
		List<Employee> list = dao.selectEmployeeGroupByDno(con, dept);
		for(Employee e : list) {
			System.out.println(e);
		}
		
		DlgEmployee dialog = new DlgEmployee();
		dialog.setEmpList(list);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private static void c3p0_ds() {
		try (Connection con = C3P0DataSource.getConnection();){
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void dbcp_ds() {
		try (Connection con = DBCPDataSource.getConnection();){
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void hikari_ds() {
		try (Connection con = Hikari_DataSource.getConnection();){
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (Connection con = Hikari_DataSource2.getConnection()){
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
