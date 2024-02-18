import java.util.Scanner;

public class Game {
    public static void introMessage() {
        System.out.println();
        System.out.println("*************************");
        System.out.println("*************************");
        System.out.println("RPG Simulator");
        System.out.println("*************************");
    }

    public static void promptCreateCharacter(Scanner userInput) {
        System.out.println();

        // ask user to name their character
        System.out.println("enter character name:");
        System.out.println();
        String characterName = userInput.nextLine();

        // ask user to choose a class
        System.out.println();
        System.out.println("enter character name:");
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        introMessage();
        promptCreateCharacter(userInput);

        userInput.close();
    }
}