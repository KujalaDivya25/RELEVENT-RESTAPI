package com.rest.dto;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

public class OTP {

	public static int generateOTP() {
		Random rand = new Random();
		int OTP = rand.nextInt(999999);;
		return OTP;
	}
}
