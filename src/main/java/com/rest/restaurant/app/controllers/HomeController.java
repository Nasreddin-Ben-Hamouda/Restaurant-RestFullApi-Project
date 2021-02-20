package com.rest.restaurant.app.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	@GetMapping("/backup/creating")
    public String creatingBackup() {
    	Process p = null;
        try {
        	System.out.print("starting backup \n");
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec("mysqldump -P 3306 -h 127.0.0.1 -uroot --add-drop-database -B restaurant -r src/main/resources/backup/restaurantbackup.sql");
            System.out.print("waiting for backup to complete \n");
            int processComplete = p.waitFor();
            if (processComplete == 0) {

            	System.out.print(" Success operation \n");

            } else {
            	System.out.print(" failed operation\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "creating_backup";
    }
	@GetMapping("/backup/restore")
    public String restoreBackup() throws IOException, InterruptedException, SQLException {
		
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password="); 
		Statement s=Conn.createStatement();
		s.executeUpdate("CREATE DATABASE restaurantbackup");
		
		String executeCmd = "mysql -P 3306 -h 127.0.0.1 --user=root --default-character-set=utf8 restaurantbackup " ;
		Process p1=Runtime.getRuntime().exec(executeCmd);
		OutputStream outputStream = p1.getOutputStream();
		FileInputStream fis = new FileInputStream("src/main/resources/backup/restaurantbackup.sql");
		InputStreamReader isr = new InputStreamReader(fis, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			sb.append(str + "\r\n");
		
		}
		
		str = sb.toString();
		str=str.replace("restaurant","restaurantbackup");
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
		writer.write(str);
		writer.flush();
		if(writer!=null){
			writer.close();
		}
		if(br!=null){
			br.close();
		}
		if(isr!=null){
			isr.close();
		}
		if(fis!=null){
			fis.close();
		}
		if(outputStream!=null){
			outputStream.close();
		}
		

       return "restore_backup";
    }

	
}
