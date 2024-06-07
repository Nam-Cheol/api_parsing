package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyHttpAlbumClient {

	public static void main(String[] args) {

		// 순수 자바코드에서 HTTP 통신
		// 1. 서버 주소 경로
		// 2. URL 클래스
		// 3. url.openConnection() <-- 스트림 I/O

		String urlStr = "https://jsonplaceholder.typicode.com/albums/5";

		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Context-type", "application/json");

			int responseCode = conn.getResponseCode();
			System.out.println("response code : " + responseCode);
			
			// HTTP 응답 메세지에 데이터를 추출 [] -- Stream --- []
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			// 스트링 빌더를 사용하는 이유는 String 의 불변성 때문에 기본 String 에 문자를 더하게 되면
			// 여러가지의 String 이 생성되게 되고, 메모리 낭비가 심해지기 때문
			StringBuilder sb = new StringBuilder();
			String line;
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			Album album = gson.fromJson(sb.toString(), Album.class);
			System.out.println(album.toString());
			
//			Album[] albums = gson.fromJson(sb.toString(), Album[].class);
//			
//			for(Album album : albums) {
//				System.out.println(album.toString());
//			}
		
			// 자원 닫는 거 신경쓰자 !
			br.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
