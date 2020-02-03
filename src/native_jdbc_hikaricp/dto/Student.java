package native_jdbc_hikaricp.dto;

public class Student {
	private int sno;
	private String sname;
	private int kor;
	private int eng;
	private int math;
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int sno, String sname, int kor, int eng, int math) {
		this.sno = sno;
		this.sname = sname;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return String.format("Student [%s, %s, %s, %s, %s]", sno, sname, kor, eng, math);
	}
	
	
	
}
