package ch01;

import com.google.gson.Gson;

public class MainTest1 {

	public static void main(String[] args) {
		// 우리 소스 코드 상에 사용하기 위해 설정이 필요하다.
		
		// 프로젝트 속성에서 빌드패스에서 클래스 패스에 add jars 등록
		Gson gson = new Gson();
		gson.fieldNamingStrategy();
		
		Student student1 = new Student();
		Student student2 = new Student("홍", 20, "컴공");
		
		Temp[] temps = {new Temp(1,2), new Temp(2, 3)};
		
		for(int i = 0; i < temps.length; i++) {
			System.out.println("-----------------");
			System.out.println(temps[i].toString());
		}
	}// end of main
	
	static class Temp {
		int id;
		int id2;
		
		public Temp(int id, int id2) {
			this.id = id;
			this.id2 = id2;
		}

		@Override
		public String toString() {
			return "Temp [id=" + id + ", id2=" + id2 + "]";
		}
		
	}
	
}// end of class
