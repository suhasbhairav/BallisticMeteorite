package com.ballistic.execution;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.ballistic.logging.LoggerMessage;
import com.ballistic.text.OutputConsole;

public class ProcessExecution {

	public ProcessExecution(){
		
		
	}
	
	public static void executeFile(String filename){
		OutputConsole.clearProgramOutput();
		StringBuilder stringBuilder = new StringBuilder();		
		ProcessBuilder processBuilder = new ProcessBuilder("python", filename);
		try {
			Process process = processBuilder.start();
			int errorCode = process.waitFor();			
			
			BufferedReader reader = null;
			if(errorCode != 0){
				try{
					reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					String line = null;
					while((line=reader.readLine())!=null){					
						stringBuilder.append(line+System.getProperty("line.separator"));
					}
			
				}catch(Exception e1){
					reader.close();
					
					LoggerMessage.printLog(ProcessExecution.class.getName(), e1.getMessage());
				}
			
			}else{
				try{
					reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line = null;
					while((line=reader.readLine())!=null){					
						stringBuilder.append(line+System.getProperty("line.separator"));
					}
				
				}catch(Exception e1){
					reader.close();					
					LoggerMessage.printLog(ProcessExecution.class.getName(), e1.getMessage());
				}
			}

			OutputConsole.setProgramOutput(stringBuilder.toString());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LoggerMessage.printLog(ProcessExecution.class.getName(), e.getMessage());
		}
	}
	
	
}
