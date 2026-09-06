import java.util.*;

public class RockPaperScissors {
    static String playRound(String p, String c) {
        if (p.equals(c)) return "Draw";
        if ((p.equals("Rock") && c.equals("Scissors")) ||
            (p.equals("Paper") && c.equals("Rock")) ||
            (p.equals("Scissors") && c.equals("Paper")))
            return "Player Wins";
        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] moves = {"Rock", "Paper", "Scissors"};
        int win = 0, loss = 0, draw = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Round " + i + " - Enter move: ");
            String p = sc.next();
            String c = moves[new Random().nextInt(3)];

            String r = playRound(p, c);
            System.out.println("Computer: " + c + " | " + r);

            if (r.equals("Player Wins")) win++;
            else if (r.equals("Computer Wins")) loss++;
            else draw++;
        }

        System.out.println("Wins: " + win + " | Losses: " + loss +
                           " | Draws: " + draw);
        System.out.println("Win %: " + (win * 100.0 / 5));
    }
}