package com.task;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Test {

	private static HttpURLConnection con;

	public static void main(String[] args) throws IOException {

		String url = "http://localhost:8080/kie-server/services/rest/server/containers/taskProj_1.0.0-SNAPSHOT/processes/instances/20";
		//String urlParameters = "name=Jack&occupation=programmer";
		//byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();

			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Basic a3Jpc3Y6a3Jpc3Y=");
			con.setRequestProperty("Content-Type", "application/json");

			/*try {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postData);
			} */}catch (Exception e) {

			}

			StringBuilder content = null;

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				content = new StringBuilder();

				while ((line = br.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			} catch (Exception e) {

			}

			System.out.println(content.toString());

		} 
	}
