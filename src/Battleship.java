import java.util.Scanner;
public class Battleship {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board board = new Board();
        boolean done = false;
        do {
            System.out.println("Enter 'a' to add a new ship, 'b' to see the board, 'p' to play, or 'q' to quit.");
            String userChoice = scan.nextLine();
            if (userChoice.equalsIgnoreCase("a")) {
                System.out.println(board);
                board.addShip(InputHelper.getRangedInt(scan, "Enter row [1-10]", 1, 10), InputHelper.getRangedInt(scan, "Enter col [1-10]", 1, 10), InputHelper.getRangedInt(scan, "Enter ship len [3-4]", 3, 4), InputHelper.getYNConfirm(scan, "Horizontal or Vertical [Y/N]"));
            }else if(userChoice.equalsIgnoreCase("b")){
                System.out.println(board);
            }else if(userChoice.equalsIgnoreCase("p")){
                if (board.foundShip(3) && board.foundShip(4)) {
                    System.out.println("Let's play");
                    done = true;
                }else{
                    System.out.println("You need 2 ships of length 3 and 4 to play.");
                }
            }else if(userChoice.equalsIgnoreCase("q")){
                System.exit(0);

            }else{
                System.out.println("Invalid input.");
            }
        } while (!done);
        boolean finished = true;
        while (finished) {
            System.out.println("Enter 's' to shoot, 'b' to see the board, and 'q' to quit.");
            String userChoice = scan.nextLine();
            if (userChoice.equalsIgnoreCase("s")) {
                int x = board.shoot(InputHelper.getRangedInt(scan,"What row would you like to shoot at [1-10]", 1,10)-1,InputHelper.getRangedInt(scan,"What column would you like to shoot at [1-10]",1,10)-1);
                if (x==0) {
                    System.out.println("miss");
                }else if(x==1){
                    System.out.println("hit");
                    if (board.gameOver()) {
                        finished = false;
                        System.out.println("You sunk all the battleships.");
                    }
                }else if(x==2){
                    System.out.println("Already guessed.");
                }else{
                    System.out.println("Invalid.");
                }
            }else if(userChoice.equalsIgnoreCase("b")){
                System.out.println(board);
            }else if(userChoice.equalsIgnoreCase("q")){
                System.exit(0);
            }
        }

    }
}

