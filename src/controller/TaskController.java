package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class TaskController {
	public static String getSO() {
		return System.getProperty("os.name");
	}

	public static void PIDTaskKill(String pid) {
		if (getSO().contains("Windows")) {
			try {
				Runtime.getRuntime().exec("cmd /c taskkill /pid "+ Integer.parseInt(pid));
			} catch (IOException e) {
				System.out.println("Erro ao executar taskkill");
			}
		}
		else {
			try {
				Runtime.getRuntime().exec("kill pid"+ pid);
			} catch (IOException e) {
				System.out.println("Erro ao executar o comando kill pid");
			}
		}
	}

	public static void NameTaskKill(String taskname) {
		if(getSO().contains("Windows")) {
			try {
				Runtime.getRuntime().exec("cmd /c taskkill /im "+ taskname);
			} catch (IOException e) {
				System.out.println("Erro ao executar taskkill");
			}
		} else {
			try {
				Runtime.getRuntime().exec("killall proc"+ taskname);
			} catch (IOException e) {
				System.out.println("Erro ao executar o comando killall");
			}
		}
	}
	
	public static void PIDorName (String task) {
		if (CheckPIDorName(task)) {
			PIDTaskKill(task);
		} else {
			NameTaskKill(task);
		}
	}

	public static String getTaskList(String SO){
		if (SO.contains("Windows")) {
			Process process;
			try {
				process = Runtime.getRuntime().exec("cmd /c tasklist");
				LineNumberReader l = new LineNumberReader(new InputStreamReader(process.getInputStream()));
				String s;
				StringBuffer stringbuffer = new StringBuffer();
				while ((s = l.readLine()) != null) {
					s.trim();
					if (s.length() > 0) {
						stringbuffer.append(s + "\n");
					}
				}
				return stringbuffer.toString();
			} catch (IOException e) {
				System.out.println("Erro ao executar o comando");
			}
			
		} else {
			Process process;
			try {
				process = Runtime.getRuntime().exec("top");
				LineNumberReader l = new LineNumberReader(new InputStreamReader(process.getInputStream()));
				String s;
				StringBuffer stringbuffer = new StringBuffer();
				while ((s = l.readLine()) != null) {
					s.trim();
					if (s.length() > 0) {
						stringbuffer.append(s + "\n");
					}
				}
			} catch (IOException e) {
				System.out.println("Erro ao executar o comando");
			}
			
		}
		return "SO não encontrado ou erro ao informar a lista de tarefas.";
	}

	public static boolean CheckPIDorName(String task) {
		boolean validnum;
		try {
			Double.parseDouble(task);
			validnum = true;
		} catch (NumberFormatException e) {
			validnum = false;
		}
		return validnum;
	}

}
