package native_jdbc_hikaricp.dto;

public class Employee {
	private int empNo;
	private String empName;
	private String title;
	private Employee manager;
	private int salary;
	private Department dept;
	
	public Employee() {}

	public Employee(int empNo) {
		this.empNo = empNo;
	}
	
	public Employee(int empNo, String empName, String title, Employee maneger, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = maneger;
		this.salary = salary;
		this.dept = dept;
	}
	
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManeger(Employee maneger) {
		this.manager = maneger;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return String.format("Employee [%s, %s, %s, %s, %s, %s]", empNo,
				empName, title, manager.getEmpNo()==0?"null":"", salary, dept.getDeptNo());
	}
	

}
