package exam1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class SlipAPI {
	
	public Map<String, Object> insertSlip(String param) {
		   Map<String, Object> map = new HashMap<String, Object>();
	        String reqURL = "http://192.168.0.83/exam/insertSlip";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            
	            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            
	           // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            bw.write(param);
	            bw.flush();
	            
	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	            
	            
	 
	            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);
	            
	            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            Gson gson = new Gson();
	            map = gson.fromJson(result, HashMap.class);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
		return map;
	}

}
