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

public class MyHttpUsersList {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/users");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Context-type", "application/json");
			
			int responseCode = conn.getResponseCode();
			System.out.println("response code : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Type userType = new TypeToken<List<User>>(){}.getType();
			List<User> userList = gson.fromJson(sb.toString(), userType);
			
			for(User user : userList) {
				System.out.println(user.toString());
			}
			
//			
//			List<User> userList = gson.fromJson(sb.toString(), userType);
//			
//			for (User user : userList) {
//				System.out.println(user.toString());
//			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
