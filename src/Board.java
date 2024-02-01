import java.util.Objects;

public class Board {
    private String[][] squares;

    public Board() {
        squares = new String[10][10];
        for (int c = 0; c < squares[0].length; c++) {
            for (int r = 0; r < squares.length; r++) {
                squares[r][c] = "- ";
            }
        }
    }
    public String toString(){
        String x = "";
        for (int c = 0; c < squares[0].length; c++) {
            for (int r = 0; r < squares.length; r++) {
                x += squares[r][c] + "";
            }
            x += "\n";
        }
        return x;
    }


    public int shoot(int row, int col){
        if (row < 0 || row >= 10 || col < 0 || col >= 10 ) {
            return -1;
        }
        String content = squares[row][col];
        if (Objects.equals(content, "-")) {
            squares[row][col] = "m";
            return 0;

        }else if (content.equals("b")){
            squares[row][col] = "x";
            return 1;
        }else if(content.equals("x") || content.equals("m")){
            return 2;
        }else{
            return 3;
        }
    }


    public boolean addShip(int row, int col, int len, boolean horizontal){
        int x = col;
        boolean h = true;
        boolean help = false;
        if (horizontal) {
            if (col + len > 10) {
                return false;
            }
            for (int r = 0; r < len; r++) {
                if (!squares[row][col].equals("-")) {
                    h = false;
                }
                col++;
            }
            col = x;
            if (h) {
                for (int r = 0; r < len; r++) {
                    squares[row][col] = "b";
                    col++;
                }
                help = true;
            }
        } else{
                if (col + len > 10) {
                    return help;
                }
                x = row;
                for (int r = 0; r < len; r++) {
                    if (!squares[row][col].equals("-")) {
                        h = false;
                    }
                    row++;
                }
                row = x;
                if (h) {
                    for (int r = 0; r < len; r++) {
                        squares[row][col] = "b";
                        row++;
                    }
                    help = true;
                }
            }
        return help;

        }





    public boolean foundShip(int len){
        if (len != 3 && len != 4) {
            return false;
        }
        return foundShipHorizontal(len) || foundShipVertical(len);
    }

    private boolean foundShipHorizontal(int len){
        for (int c = 0; c < squares.length; c++) {
            for (int r = 0; r < squares[0].length; r++) {
               boolean ship = true;

                for (int i = 0; i < len; i++) {
                    if (!Objects.equals(squares[c][r + i], "b")) {
                        ship = false;
                        break;
                    }
                }
                if (ship) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean foundShipVertical(int len){
        for (int c = 0; c < squares.length - len; c++) {
            for (int r = 0; r < squares[0].length; r++) {
                boolean ship = true;

                for (int i = 0; i < len; i++) {
                    if (!Objects.equals(squares[c + i][r], "b")) {
                        ship = false;
                        break;
                    }
                }
                if (ship) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean gameOver(){
        boolean end = false;
        for (int c = 0; c < squares[0].length; c++) {
            for (int r = 0; r < squares.length; r++) {
                if (squares[r][c].equals("b")) {
                    end = true;
                    return end;
                }
            }
        }
        return end;
    }
}






