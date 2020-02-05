package native_jdbc_hikaricp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_hikaricp.LogUtil;
import native_jdbc_hikaricp.daoimpl.EmployeeDaoImpl;
import native_jdbc_hikaricp.ds.MysqlDataSource;
import native_jdbc_hikaricp.dto.Department;
import native_jdbc_hikaricp.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private Connection con;
	private static EmployeeDao dao;
	private static File imagesDir;
	private static File picsDir;
	// 테스트할 메소드의 수행전과 수행후를 보는게 테스트클래스다

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LogUtil.prnLog("setUpBeforeClass()");
		dao = EmployeeDaoImpl.getInstance();
		
		// 수행하면 데이터베이스 연결되고
		picsDir = new File(System.getProperty("user.dir")+ File.separator + "pics" + File.separator);
		if(!picsDir.exists()) {
			picsDir.mkdir();
			//picsDir 폴더가없으면 만드는 함수
		}
		imagesDir = new File(System.getProperty("user.dir")+ File.separator + "images" + File.separator);
		//파일구분자가 운영체제마다 다를수 있기때문에 앞뒤로 separator을 써줌 원래 우리는 \\써 줬다
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LogUtil.prnLog("tearDownAfterClass()");
		dao = null;
		// 끝나면 종료됨
	}

	@Before
	public void setUp() throws Exception {
		LogUtil.prnLog("setUp()");
		con = MysqlDataSource.getConnection();
		// 메소드의 수행전과

	}

	@After
	public void tearDown() throws Exception {
		LogUtil.prnLog("tearDown()");
		con.close();
		// 메소드의 수행후
	}

	@Test
	public void test01selectEmployeeByEmpNo() {
		LogUtil.prnLog("test01selectEmployeeByEmpNo()");
		Employee emp = new Employee(1004);
		try {
			Employee selectedEmp = dao.selectEmployeeByEmpNo(con, emp);
			if(selectedEmp.getPic()!=null) {
				getImageTopic(selectedEmp.getPic(),selectedEmp.getEmpNo());
				//프로젝트 폴더의 pics폴더에 사원번호,jpg파일이 생성
			}
			Assert.assertNotNull(selectedEmp);
			// 여기가 null이면 에러가 뜸(AssertionError)
			LogUtil.prnLog(selectedEmp);
		} catch (RuntimeException e) {
			LogUtil.prnLog(e.getMessage());
			e.printStackTrace();
		}
	}

	private void getImageTopic(byte[] pic, int empNo) {
		File file = new File(picsDir, empNo+".jpg");
		try(FileOutputStream fis = new FileOutputStream(file)){
			fis.write(pic);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test02SelectEmployeeByAll() throws SQLException {
		LogUtil.prnLog("test02SelectEmployeeByAll()");
		List<Employee> lists = dao.selectEmployeeByAll(con);
		Assert.assertNotEquals(0, lists.size());
		for (Employee e : lists)
			LogUtil.prnLog(e);
		// 0이 아니라면 제대로 데이터가 불러와졌다는 의미
		// EmployeeDao에서 selectEmployeeByAll을 throws SQLException 해줘서 여기도 해준다
	}

	@Test
	public void test03SelectEmployeeGroupByDno() {
		LogUtil.prnLog("test03SelectEmployeeGroupByDno()");
		Department dept = new Department();
		dept.setDeptNo(2);
		List<Employee> lists;
		try {
			lists = dao.selectEmployeeGroupByDno(con, dept);
			Assert.assertNotEquals(0, lists.size());
			for (Employee e : lists)
				LogUtil.prnLog(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 에러가뜰때 무슨에런지 확인하고싶으면 try catch로 확인
	}

	@Test
	public void test06DeleteEmployee() {
		LogUtil.prnLog("test06DeleteEmployee()");
	}

	@Test
	public void test04InsertEmployee(){
		LogUtil.prnLog("test04InsertEmployee()");
		Employee emp = new Employee(1004, "서현진", "사원", new Employee(1003), 1500000, new Department(1), getImage("seohyunjin.jpg"));
		LogUtil.prnLog(emp);
		int res;
		try {
			res = dao.insertEmployee(con, emp);
			Assert.assertEquals(res, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte[] getImage(String imgName) {
//		byte[] pic = null;
		File file = new File(imagesDir, imgName);
//		logger.debug(file.getAbsolutePath()); 이미지폴더 잘 만들어졌는지 확인
		try(InputStream is= new FileInputStream(file)){
			byte[] pic = new byte[is.available()];
			is.read(pic);
			return pic;
			//read에서 파일을 읽어서 pic에 담아주겠다
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void test05UpdateEmployee(){
		LogUtil.prnLog("test05UpdateEmployee()");
		Employee emp = new Employee(3011, "이수민", "부장", new Employee(4377), 2500000, new Department(3));
		int res;
		try {
			emp.setPic(getImage("lyy.jpg"));
			res = dao.updateEmployee(con, emp);
			LogUtil.prnLog(res);
			Assert.assertEquals(1, res);	
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
