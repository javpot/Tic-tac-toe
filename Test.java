import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();
        CPUPlayer cpu = new CPUPlayer(Mark.O);
        Scanner input = new Scanner(System.in);
        System.out.println("Vous êtes le joueur X");

        boolean gameOver = false;
        board.print();
        while (!gameOver) {
            System.out.print("Entrez votre ligne (0, 1, 2): ");
            int row = input.nextInt();
            System.out.print("Entrez votre colonne (0, 1, 2): ");
            int col = input.nextInt();
            Move playerMove = new Move(row, col);
            if (board.getPossibleMoves().stream().anyMatch(m -> m.getRow() == row && m.getCol() == col)) {
                board.play(playerMove, Mark.X);
                board.print();
                if (board.evaluate(Mark.X) == 100) {
                    System.out.println("Vous avez gagné !");
                    gameOver = true;
                    continue;
                } else if (board.getPossibleMoves().isEmpty()) {
                    System.out.println("Match nul !");
                    gameOver = true;
                    continue;
                }
            } else {
                System.out.println("Coup invalide, réessayez.");
                continue;
            }

            System.out.println("Tour de l'ordinateur...");
            Move cpuMove = cpu.getNextMoveAB(board).getFirst();
            board.play(cpuMove, Mark.O);
            board.print();
            if (board.evaluate(Mark.O) == 100) {
                System.out.println("L'ordinateur a gagné !");
                gameOver = true;
            } else if (board.getPossibleMoves().isEmpty()) {
                System.out.println("Match nul !");
                gameOver = true;
            }
        }
        input.close();


//       Board board = new Board();
//       System.out.println(board);
//       board.play(new Move(0,0), Mark.X);
//        System.out.println(board);
//        board.play(new Move(0,1), Mark.X);
//        board.play(new Move(0,2), Mark.X);
//        System.out.println(board.evaluate(Mark.X));
//        System.out.println(board);

    }
}
