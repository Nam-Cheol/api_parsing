package ch02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * {
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
   }
 */
// DTO -- 데이터 트랜스퍼 오브젝트
// private -->  Gson lib --> 변수에 접근해서 json 값을 넣어준다.
// setter가 필요하다.

@Data
@AllArgsConstructor
@ToString

public class Album {
	private int userId;
	private int id;
	private String title;
	
}
