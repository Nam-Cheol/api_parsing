package ch01;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {

	public static void main(String[] args) {
		
		Student student1 = new Student("고길동", 40, "애완학과");
		Student student2 = new Student("둘리", 5, "문제학과");
		
		//
		Student[] students = {student1, student2};
		
		// --> 특정 형식 (구조화) 되어 있는 문자열로 변환하고 싶다 -> json 형태로 변환
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// setPrettyPrinting -> \n 이 포함되어 이쁘게 출력하는 옵션
		
		// 객체를 --> json 형식의 문자열로 변환시켜보자. -> 메소드 toJson()
		String student1Str = gson.toJson(student1);
//		System.out.println(student1Str);
		
		Gson gson2 = new Gson();
		String student2Str = gson2.toJson(student2);
//		System.out.println(student2Str);
		
		// 객체에서 ---> 문자열 형태로 파싱 --> 반대로는?
		// 문자열에서 클래스 형태로 어떻게 변경할까? --> 역직렬화
		
		Student student3 = gson.fromJson(student1Str, Student.class);
//		System.out.println(student3.getName());
		
		
//		Dog[] dog = {new Dog("루키", 9999), new Dog("리아", 9)};
//		ArrayList<String> dogList = new ArrayList<>();
//		Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
//		for(int i = 0; i < dog.length; i++) {
//			dogList.add(gson3.toJson(dog[i]));
//			System.out.println(dogList.get(i));
//		}
		
		Dog[] dog = {new Dog("루키", 9999), new Dog("리아", 9)};
		Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
		for(int i = 0; i < dog.length; i++) {
			System.out.println(gson3.toJson(dog[i]));
		}
		
	}
	
}
