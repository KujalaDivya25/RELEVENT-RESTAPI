package com.rest.dto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SMS{
	public static void registration(String to, String text) {
		try {
			// Construct data
			String apiKey = "apikey=" + "Htp9VfdI/gs-woOyISBR3GyxtqIK7DEmL8jMa86PMN";
			String message = "&message=" + text;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + to;

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println(data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			System.out.println(stringBuffer.toString());
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			System.out.println("Error "+e);
		}
	}
}