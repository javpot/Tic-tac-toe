public class Test {
    public static void main(String[] args) {
       Board board = new Board();
       System.out.println(board);
       board.play(new Move(0,0), Mark.X);
        System.out.println(board);
        board.play(new Move(0,1), Mark.X);
        board.play(new Move(0,2), Mark.X);
        System.out.println(board.evaluate(Mark.X));
        System.out.println(board);

    }
}
