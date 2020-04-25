package inc.park;

import inc.park.process.CommandParser;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CommandParser cmp = new CommandParser();
        Scanner scanner = new Scanner(System.in);

        do {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else {
                String result = cmp.process(s);
                System.out.println(result);
            }
        } while (true);
    }
}
