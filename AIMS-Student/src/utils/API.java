package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class: cung cap cac phuong thuc giup gui request len server và nhan du lieu tra ve
 * Date: 9/12/2021
 * @author Do Kim Tra 20183999
 * @version 1.0
 *
 */
public class API {

	/**
	 * Thuoc tinh giup format ngay thang theo dinh dang
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * Thuoc tinh giup log ra thong tin ra console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * thiet lap ket noi va gui yeu cau, du lieu len server
	 * @param url: duong dan toi server
	 * @param method: giao thuc api
	 * @param token: doan ma cam cung cap de xac thuc nguoi dung
	 * @return connection
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static HttpURLConnection sendRequest(String url, String method, String token, String payload)
			throws MalformedURLException, IOException, ProtocolException {
		//phan 1: set up connection and send request to server
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		if(method.equals("POST")) {
			Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			writer.write(payload);
			writer.close();
		}
		return conn;
	}
	
	/**
	 * Phuong thuc nhan du lieu tu server
	 * @param conn: ket noi da duoc gui yeu cau len server
	 * @return: response: phan hoi tu server
	 * @throws IOException
	 */
	private static String receiveResponse(HttpURLConnection conn) throws IOException {
		//phan 2: doc du lieu tra ve tu server
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		return response.toString();
	}
	
	/**
	 * Phuong thuc giup goi cacs api dang GET
	 * @param url: duong dan toi server de gui request
	 * @param token: doan ma ban can de xac thuc nguoi dung
	 * @return response: phan hoi tu server (format: string)
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");
		HttpURLConnection conn = sendRequest(url, "GET", null, token);
		String response = receiveResponse(conn);
		LOGGER.info("Respone Info: " + response.toString());
		return response;
		
	}
	
	/**
	 * Phuong thuc giup goi cacs api dang POST (thanh toan, ...)
	 * @param url: duong dan toi server de gui request
	 * @param data: du lieu dua len server de xu ly (format JSON)
	 * @return response: phan hoi tu server (format String)
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + data + "\n");
		HttpURLConnection conn = sendRequest(url, "POST", data, token);
		String response = receiveResponse(conn);
		LOGGER.info("Respone Info: " + response.toString());
		return response;
	}
}
