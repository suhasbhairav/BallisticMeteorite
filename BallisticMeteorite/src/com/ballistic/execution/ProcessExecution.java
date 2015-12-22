package com.ballistic.execution;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ballistic.logging.LoggerMessage;

public class ProcessExecution {

	public ProcessExecution(){
		
		
	}
	
	public static void executeFile(String filename){
		//OutputConsole.clearProgramOutput();
		ProcessBuilder processBuilder = new ProcessBuilder("python", filename);
		try {
			Process process = processBuilder.start();
			int errorCode = process.waitFor();
			System.out.println("Error:"+errorCode);
			InputStream inputStream = process.getInputStream();
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader reader = null;
			try{
				reader = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				while((line=reader.readLine())!=null){
					stringBuilder.append(line+System.getProperty("line.separator"));
				}
				System.out.println(stringBuilder.toString());
			}catch(Exception e1){
				reader.close();
				System.out.println(e1.getMessage());
				LoggerMessage.printLog(ProcessExecution.class.getName(), e1.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerMessage.printLog(ProcessExecution.class.getName(), e.getMessage());
		}
	}
	
	
}
