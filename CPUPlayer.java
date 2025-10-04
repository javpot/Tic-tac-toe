import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark maxPlayer;
    private Mark minPlayer;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.maxPlayer = cpu;
        this.minPlayer = (cpu == Mark.X) ? Mark.O : Mark.X;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    private int minMax(Board board, Mark currentPlayer) {
        numExploredNodes++;

        int eval = board.evaluate(maxPlayer);
        if (eval == 100 || eval == -100 || board.getPossibleMoves().isEmpty()) {
            return eval;
        }

        if (currentPlayer == maxPlayer) {
            int score = Integer.MIN_VALUE;

            for (Move m : board.getPossibleMoves()) {
                board.play(m, currentPlayer);
                score = Math.max(score, minMax(board, minPlayer));
                board.undo(m);
            }

            return score;
        } else {
            int score = Integer.MAX_VALUE;

            for (Move m : board.getPossibleMoves()) {
                board.play(m, currentPlayer);
                score = Math.min(score, minMax(board, maxPlayer));
                board.undo(m);
            }

            return score;
        }
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move m : board.getPossibleMoves()) {
            board.play(m, maxPlayer);
            int score = minMax(board, minPlayer);
            board.undo(m);
            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(m);
            } else if (score == bestScore) {
                bestMoves.add(m);
            }
        }
        return bestMoves;
    }

    private int alphaBeta(Board board, Mark currentPlayer, int alpha, int beta) {
        numExploredNodes++;

        int eval = board.evaluate(maxPlayer);
        if (eval == 100 || eval == -100 || board.getPossibleMoves().isEmpty()) {
            return eval;
        }

        if (currentPlayer == maxPlayer) {
            int score = Integer.MIN_VALUE;
            for (Move m : board.getPossibleMoves()) {
                board.play(m, currentPlayer);
                score = Math.max(score, alphaBeta(board, minPlayer, alpha, beta));
                board.undo(m);
                if (score >= beta) break;
                alpha = Math.max(alpha, score);
            }
            return score;
        } else {
            int score = Integer.MAX_VALUE;

            for (Move m : board.getPossibleMoves()) {
                board.play(m, currentPlayer);
                score = Math.min(score, alphaBeta(board, maxPlayer, alpha, beta));
                board.undo(m);
                if (score <= alpha) break;
                beta = Math.min(beta, score);

            }
            return score;
        }
    }


    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move m : board.getPossibleMoves()) {
            board.play(m, maxPlayer);
            int score = alphaBeta(board, minPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);
            board.undo(m);
            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(m);
            } else if (score == bestScore) {
                bestMoves.add(m);
            }
        }
        return bestMoves;
    }

}