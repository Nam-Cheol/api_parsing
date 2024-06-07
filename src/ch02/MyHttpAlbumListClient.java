package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MyHttpAlbumListClient {

	public static void main(String[] args) {

		String urlStr = "https://jsonplaceholder.typicode.com/albums";

		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Context-type", "application/json");

			int responseCode = conn.getResponseCode();
			System.out.println("response code : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// 스트링 빌더를 사용하는 이유는 String 의 불변성 때문에 기본 String 에 문자를 더하게 되면
			// 여러가지의 String 이 생성되게 되고, 메모리 낭비가 심해지기 때문
			StringBuilder sb = new StringBuilder();
			String line;
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			// Gson 에서 제공하는 Type 이라는 데이터 타입을 활용할 수 있다.
			// Json 배열 형태를 쉽게 파싱하는 방법 -> TypeToken 안에 List<T> 을 활용한다.
			
			Type albumType = new TypeToken<List<Album>>(){}.getType();
			List<Album> albumList = gson.fromJson(sb.toString(), albumType);
			System.out.println(albumList.size());
			
			for(Album album : albumList) {
				System.out.println(album.toString());
			}
			
		
			// 자원 닫는 거 신경쓰자 !
			br.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
