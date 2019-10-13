package view;

public class AdventuringView extends View {

    public int nextDungeon() {
        System.out.println("Enter the next dungeon: Yes[0]; No[1]");
        int answer = getAnswer();

        if (answer == 1 || answer == 0) {
            return answer;
        } else {
            nextDungeon();
        }
        return -1;
    }

    public void displayEndOfAdventure() {
        System.out.println("After the adventure our hero goes home.");
    }
}
