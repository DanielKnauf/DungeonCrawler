package view.AdventureGame;

import java.util.ArrayList;
import java.util.Scanner;

import model_AdventureGame.theWorld.Direction;
import model_AdventureGame.theWorld.Room;

public class View {

	private static Scanner inputScanner = new Scanner(System.in);

	public View() {
		// TODO Auto-generated constructor stub
	}

	public static Direction choooseNextMove(Room playerRoom) {
		// Display options
		System.out.println("________");
		ArrayList<Direction> possibleDirections = playerRoom.getDirections();
		System.out.println("Choose the next room you want to explore.");
		int counter = 0;
		for (Direction d : possibleDirections) {
			System.out.println("[" + counter + "]: " + d.getName());
			counter++;
		}

		// Get answer from player
		int answer = -1;
		while (answer < 0 || answer >= possibleDirections.size()) {
			System.out.println("Your move: ");
			answer = inputScanner.nextInt();
			if (answer < 0 || answer >= possibleDirections.size()) {
				System.out.println("FAIL. Choose again.");
			}
		}
		return possibleDirections.get(answer);
	}

	public static int nextDungeon() {
		System.out.println("Enter the next dungeon: Yes[0]; No[1]");
		int answer = inputScanner.nextInt();

		if (answer == 1 || answer == 0) {
			return answer;
		} else {
			nextDungeon();
		}
		return -1;
	}

	public void displayPlayerPositon(Room playerRoom) {
		System.out.println("--> Player is at room " + playerRoom.getRow() + " | " + playerRoom.getColumn());
	}
}
