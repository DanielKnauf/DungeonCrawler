package view.AdventureGame;

import java.util.ArrayList;
import java.util.Scanner;

import model_AdventureGame.theComponents.GameFigure;
import model_AdventureGame.theComponents.Hero;
import model_AdventureGame.theWorld.Direction;
import model_AdventureGame.theWorld.Dungeon;
import model_AdventureGame.theWorld.Room;

public class View {

	private static Scanner inputScanner = new Scanner(System.in);

	public View() {
		// TODO Auto-generated constructor stub
	}

	public Direction choooseNextMove(Room playerRoom) {
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

	public int nextDungeon() {
		System.out.println("Enter the next dungeon: Yes[0]; No[1]");
		int answer = inputScanner.nextInt();

		if (answer == 1 || answer == 0) {
			return answer;
		} else {
			nextDungeon();
		}
		return -1;
	}

	public void displayDungeon(Dungeon dungeon) {
		Room[][] dungeonMap = dungeon.getDungeonMapp();
		System.out.println("\nMap of the Dungeon");
		for (int row = 0; row < dungeon.getRowSize(); row++) {
			System.out.print(row);
			for (int colm = 0; colm < dungeon.getColmSize(); colm++) {

				if (dungeonMap[row][colm] == null) {
					System.out.print(" ####### ");
				} else {
					System.out.print(displayRoom(dungeonMap[row][colm]));
				}
			}
			System.out.print("\n");
		}
		displayPlayerPositon(dungeon.getPlayerRoom());
	}

	private void displayPlayerPositon(Room playerRoom) {
		System.out.println("--> Player is at room " + playerRoom.getRow() + " | " + playerRoom.getColumn());
	}

	private String displayRoom(Room room) {
		String roomDisplay = " [ _._ ] ";
		// ⇡⇠⇢⇣
		if (room.getHasPlayer() && room.getHasMonster()) {
			roomDisplay = roomDisplay.replace("_._", "PvM");
		}
		if (room.getHasPlayer()) {
			roomDisplay = roomDisplay.replace('.', 'P');
		}

		if (room.getHasMonster()) {
			roomDisplay = roomDisplay.replace('.', 'M');
		}

		if (room.checkForExit()) {
			roomDisplay = roomDisplay.replace('.', 'E');
		}

		return roomDisplay;
	}

	public void playerEntersDungeon(Dungeon dungeon) {
		System.out.println("Player enters the deep dark dungeon.");
		displayDungeon(dungeon);

	}

	public void displayHealth(GameFigure gameFigure) {
		System.out.println("Health of " + gameFigure.getName() + ":: " + gameFigure.displayHitpoints() + "\n");
	}

	public void heroLeavesDungeon(Hero hero) {
		System.out.println("________\nEnd of Dungeon" + "\nHero gets out of the dungeon alive.");
		displayHealth(hero);
	}
}
