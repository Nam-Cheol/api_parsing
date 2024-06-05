package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import lombok.Getter;

@Data
public class APIExplorer3 {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		// 순수 자바코드로 (클라이언트 측 코딩)
		// 준비물
		// 1. 서버 측 주소 - 경로
		// StringBuilder -> 단일 스레드, 버퍼는 멀티 스레드
		// http://localhost:8080/test?name=홍길동&age=20(쿼리 스트링 방식) 와 동일하다 -> 엔드 포인트
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20 --> url
		// 인코딩을 한 것
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=h2L2yUU0MSS86UJbB3slU5ZNshufRpQ9eXTCMo2%2Bm2hLIP8rh%2B%2BwQHrNiRWw%2BrSkuQkgHU1H%2FdslG3QfM7cRvQ%3D%3D"); /*
																																 * Service
																																 * Key
																																 */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2024", "UTF-8")); /* 측정 연도 */
		urlBuilder.append("&" + URLEncoder.encode("itemCode", "UTF-8") + "="
				+ URLEncoder.encode("PM10", "UTF-8")); /* 미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략 */

		URL url = new URL(urlBuilder.toString());
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
		
	

//		System.out.println(sb.toString());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		MResponse mResponse = gson.fromJson(sb.toString(), MResponse.class);
		
		for(int i = 0; i < mResponse.response.body.items.size(); i++) {
			System.out.println("==================================");
			mResponse.response.body.items.get(i).showDust();
		}
		
		
		
//		Items[] items = gson.fromJson(sb.toString(), Items[].class);
//		
//		for(int i = 0; i < items.length; i++) {
//			items[i].toString();
//		}
		
		rd.close();
		conn.disconnect();

	}// end of main

	@Data
	class MResponse {
		Response response;
	}
	
	@Data
	class Response {
		Body body;
		Header header;
	}
	
	@Data
	class Body {
		int totalCount;
		List<Item> items = new ArrayList<>();
		int pageNo;
		int numOfRows;
	}
	
	@Data
	class Header {
		String resultMsg;
		String resultCode;
		
	}
	
	@Data
	class TotalCount {
		String totalCount;
	}
	
	@Data
	class Item {
		
		String clearVal;
		String sn;
		String districtName;
		String dataDate;
		String issueVal;
		String issueTime;
		String clearDate;
		String issueDate;
		String moveName;
		String clearTime;
		String issueGbn;
		String itemCode;
		
		public void showDust() {
			System.out.println(districtName + "의 미세먼지는 '" + issueGbn + "' 입니다.");
		}
		
		@Override
		public String toString() {
			return "Items [clearVal=" + clearVal + ", sn=" + sn + ", districtName=" + districtName + ", dataDate="
					+ dataDate + ", issueVal=" + issueVal + ", issueTime=" + issueTime + ", clearDate=" + clearDate
					+ ", issueDate=" + issueDate + ", moveName=" + moveName + ", clearTime=" + clearTime + ", issueGbn="
					+ issueGbn + ", itemCode=" + itemCode + "]";
		}
		
		
	}
	
}// end of class
