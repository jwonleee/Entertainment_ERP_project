package com.y4j.final_project.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("google")
@Service
public class GoogleAPI {
	
	public String getAccessToken(String code , String state) {

		String requestURL= "https://oauth2.googleapis.com/token";
		String redirect_uri = "http://localhost:8888/user/google"; 

		String access_token="";
		String refresh_token="";

		//post의 폼데이터 형식 키=값&키=값..으로 필요한 Parameter값 가져오기
		String data = "grant_type=authorization_code"
				+ "&client_id=574838606732-5jk20nvt1nm2egk3j533uvgntkd08v1t.apps.googleusercontent.com"
				+ "&client_secret=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"
				+ "&redirect_uri=" + redirect_uri
				+ "&code=" + code
				+ "&state=" + state;
		
		try {

			URL url = new URL(requestURL);
			//해당 http주소로 연결하는 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setRequestMethod("POST"); //post형식
			conn.setDoOutput(true); //구글 서버로부터 데이터 응답을 허용

			//데이터 전송을 위한 클래스
			//			OutputStream out = conn.getOutputStream();
			//			OutputStreamWriter osw = new OutputStreamWriter(out);
			//			BufferedWriter bw = new BufferedWriter(osw);

			//위의 작업을 한 줄로
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(data);
			bw.flush(); //connection이 연결되어있는 곳으로 파라미터 값이 날아감

			//응답결과를 conn객체에서 받음
			//System.out.println(conn.getResponseCode()); //성공 시:200
			if(conn.getResponseCode() == 200) { //요청 성공 시
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String result = "";
				String str = null;

				while((str = br.readLine()) != null) { //한 줄씩 데이터 읽음, 읽을 값이 없다면 null 반환
					result += str;
				}
				//System.out.println(result);

				//JSON 데이터 분해
				JsonParser json = new JsonParser(); //com.google.~~
				JsonElement element = json.parse(result); //json데이터 전
				JsonObject obj = element.getAsJsonObject(); //json오브젝트 형 변환
				access_token = obj.get("access_token").getAsString(); //json데이터를 문자열로 형 변환
				refresh_token = obj.get("refresh_token").getAsString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}
	
	//토큰 기반으로 유저 정보 얻기
			public Map<String, Object> getUserInfo(String access_token){
				
				Map<String, Object> map = new HashMap<>();
				String requestURL = "https://people.googleapis.com/v1/people/me";
				
				
				try {
					URL url = new URL(requestURL);
					//해당 http주소로 연결하는 
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				
					conn.setRequestMethod("POST"); //post형식
					conn.setDoOutput(true); //구글 서버로부터 데이터 응답을 허용
					
					//헤더에 토큰 정보 추가
					conn.setRequestProperty("Authorization", "Bearer " + access_token);
					//System.out.println("토큰 요청 결과: " + conn.getResponseCode()); //200
					
					if(conn.getResponseCode() == 200) { //요청 성공 시
						
						BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						
						String result = "";
						String str;
						
						while((str = br.readLine()) != null) { //한 줄씩 데이터 읽음, 읽을 값이 없다면 null 반환
							result += str;
						}
						System.out.println(result);
						
				        JsonParser json = new JsonParser();
				        JsonElement element = json.parse(result);
				        
				        //json에서 key를 꺼내고, 다시 key를 꺼낸다.
				        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
				        JsonObject google_account = element.getAsJsonObject().get("google_account").getAsJsonObject();
				        
				        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
				        String email = google_account.getAsJsonObject().get("email").getAsString();
				        
				        map.put("nickname", nickname);
				        map.put("email", email);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return map;
	
			}

}
