import java.util.ArrayList;
import java.util.Arrays;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;


    // Ne pas changer la signature de cette méthode
    public Board() {
        board = new Mark[3][3];
        for (Mark[] marks : board) {
            Arrays.fill(marks, Mark.EMPTY);
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        int row = m.getRow();
        int col = m.getCol();
        if(board[row][col] == Mark.EMPTY){
            board[row][col] = mark;
            evaluate(mark);
        }
        else{
            System.out.println("Invalid move");
        }

    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        if(mark == Mark.EMPTY){
            return 0;
        }
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return 100;
            }
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                return 100;
            }
        }
        // Check diagonals
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return 100;
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return 100;
        }
        // Check for opponent win
        Mark opponent = (mark == Mark.X) ? Mark.O : Mark.X;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == opponent && board[i][1] == opponent && board[i][2] == opponent) {
                return -100;
            }
            if (board[0][i] == opponent && board[1][i] == opponent && board[2][i] == opponent) {
                return -100;
            }
        }
        if (board[0][0] == opponent && board[1][1] == opponent && board[2][2] == opponent) {
            return -100;
        }
        if (board[0][2] == opponent && board[1][1] == opponent && board[2][0] == opponent) {
            return -100;
        }
        // Check for draw or ongoing game
        for (Mark[] marks : board) {
            for (Mark m : marks) {
                if (m == Mark.EMPTY) {
                    return 0; // Game is still ongoing
                }
            }
        }
        return 0; // Draw
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Mark[] marks : board) {
            for (Mark mark : marks) {
                sb.append(mark).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
