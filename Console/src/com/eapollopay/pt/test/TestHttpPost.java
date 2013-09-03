package com.eapollopay.pt.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class TestHttpPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//assembly HTTP Body
		String BodyData = "id=janway";
		//connect to proxy
		System.out.println("BodyData="+BodyData);
		String APIPath = "/checkUserDup.jsp";
		System.out.println("APIPath=" + APIPath);
		String Host = "pay.wowkool.com";
		//String Host = "127.0.0.1";
		System.out.println("Host=" + Host);
		String resString = "";
		try {
			/*
			System.out.println("init socketFactory provider");
			java.security.Security.setProperty("ssl.SocketFactory.provider", "com.sun.net.ssl.internal.ssl.SSLSocketFactoryImpl");
			SocketFactory factory = SSLSocketFactory.getDefault();
			Socket socket = factory.createSocket(Host, 443);*/
			
			System.out.println("init socketFactory provider");
			//java.security.Security.setProperty("ssl.SocketFactory.provider", "com.sun.net.ssl.internal.ssl.SSLSocketFactoryImpl");
			SocketFactory factory = SocketFactory.getDefault();
			Socket socket = factory.createSocket(Host, 80);
			
			BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("start to write");
			socketOut.write("POST " + APIPath + " HTTP/1.0\n");
			socketOut.write("Content-Type: application/x-www-form-urlencoded\n");
			socketOut.write("Host: " + Host + "\n");
			socketOut.write("Content-Length: " + BodyData.length() + "\n\n");
			socketOut.write(BodyData);
			socketOut.write("\n");
			socketOut.flush();
			
			System.out.println("read response from "+Host);
			//receive data
			String line;
			while((line = socketIn.readLine()) != null){
				System.out.println("line=" + line);
				if(line.indexOf("PRC")>=0){
				     resString = line;
				     break;
				}
			}
			socketOut.close();
			socketIn.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String BankResponseCode  = "";
		String ApprovalCode      = "";
		String BatchNumber       = "";

		int prc = 0;
		int src = 0;
		//dissect data
		if(resString.length()>0){
			int svrResp = 1;
			StringTokenizer r = new StringTokenizer(resString,"&");
			while(r.hasMoreTokens()) {
			 	
			}
			System.out.println("prc="+String.valueOf(prc)+", src="+String.valueOf(src));
		}
	}

}
