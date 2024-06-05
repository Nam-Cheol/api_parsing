package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExplorer {

	public static void main(String[] args) throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String urlStr = "https://jsonplaceholder.typicode.com/todos";
		
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		System.out.println("Response code: " + conn.getResponseCode());
		
		BufferedReader rd; 
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		User[] users = gson.fromJson(sb.toString(), User[].class);
		
		for (int i = 0; i < users.length; i++) {
//			System.out.println("=================");
//			System.out.println(users[i].toString());
			users[i].showInfo();
		}
		
	}
	
	class User {
		int userId;
		int id;
		String title;
		boolean completed;
		
		public void showInfo() {
			System.out.println("=========================");
			System.out.println("userId : " + userId);
			System.out.println("id : " + id);
			System.out.println("title : " + title);
			System.out.println("completed : " + completed);
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", id=" + id + ", title=" + title + ", completed=" + completed + "]";
		}
		
		
		
	}
}
