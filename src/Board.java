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
        if (horizontal) {
            if (col + len > 9) {
                return false;
            }
            for (int c = col; c < col + len; c++) {
                if (squares[row][c].equals("b")) {
                    return false;
                }
            }
            for (int c = col; c < col + len; c++) {
                squares[row][c] = "b";
            }
        }else{
            if (row + len > 9) {
                return false;
            }
            for (int r = row; r < row + len; r++) {
                if (squares[r][col].equals("b")) {
                    return false;
                }
            }
            for (int r = row; r < row + len; r++) {
                squares[r][col] = "b";
            }
        }
        return true;
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






