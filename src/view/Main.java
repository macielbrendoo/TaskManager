package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Scanner;

import controller.TaskController;

public class Main {
	private static Scanner scan;

	public static void main(String[] args) throws HeadlessException, IOException {
		
		scan = new Scanner(System.in);
		System.out.println(TaskController.getTaskList(TaskController.getSO()));
		System.out.println("Digite o nome ou PID da tarefa que deseja encerrar:");
		TaskController.PIDorName(scan.nextLine());
		
		
	}

}
