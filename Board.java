/**
 * Name: Keshava Katti
 * PennKey: kattikes
 * Recitation: 212
 * 
 * Description: creates game board for connect 4 play
 * 
 */

public class Board {
    //fields
    private char[][] arrayBoard;
    private int height = 6;
    private int width = 7;
    private int moveNumber = 0;
    private int moveNumberAuto = 0;
    private String winner = "";
    private String redWins = "AAAA";
    private String greenWins = "BBBB";
    
    //constructor
    public Board() {
        PennDraw.clear(PennDraw.WHITE);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(26);
        PennDraw.text(0.5, 0.945, "CONNECT 4");
        arrayBoard = new char[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                arrayBoard[row][column] = '0';
                PennDraw.setPenColor(PennDraw.BLUE);
                PennDraw.filledSquare((1.0 / 14.0) * ((2 * column) + 1), 
                                      (1.0 / 14.0) * ((2 * row) + 1), 1.0 / 14.0);
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledCircle((1.0 / 14.0) * ((2 * column) + 1), 
                                      (1.0 / 14.0) * ((2 * row) + 1), 1.0 / 17.0);
                
            }
        }
    }
    
    //check if column is full
    public boolean isColumnFull(int dropColumn) {
        int counter = 0;
        for (int row = height - 1; row >= 0; row--) {
            if (arrayBoard[row][dropColumn] != '0') {
                counter++;
            }
        }
        if (counter == height) {
            return true;
        } else {
            return false;
        }
    }
    
    //randomly determine which team will start first
    public void firstMove() {
        double random = Math.random();
        PennDraw.setFontSize(14);
        if (random >= 0.0 && random < 0.5) {
            moveNumber = 0;
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.text(0.85, 0.92, "It is Red's turn");
        } else {
            moveNumber = 1;
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.text(0.15, 0.92, "It is Green's turn");
        }
    }
    
    //with auto, randomly determine which team will start first
    public void firstMoveAuto() {
        double random = Math.random();
        if (random >= 0.0 && random < 0.5) {
            moveNumberAuto = 0;
        } else {
            moveNumberAuto = 1;
        }
    }
    
    //find first empty space in column
    public int firstEmptySpace(int dropColumn) {
        int dropRow = 0;
        for (int row = 5; row >= 0; row--) {
            if (arrayBoard[row][dropColumn] == '0') {
                dropRow = row;
                break;
            }
        }
        return dropRow;
    }
    
    //insert disk
    public void insert(int dropColumn) {
        if (dropColumn < 0 || dropColumn > width - 1) {
            throw new RuntimeException("ERROR: Out of bounds column");
        }
        if (isColumnFull(dropColumn) == true) {
            //do nothing
        } else {
            int row = firstEmptySpace(dropColumn);
            if (moveNumber % 2 == 0) {
                PennDraw.setFontSize(14);
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.9, 0.92, 0.2, 0.05);
                PennDraw.setPenColor(PennDraw.GREEN);
                PennDraw.text(0.15, 0.92, "It is Green's turn");
                arrayBoard[row][dropColumn] = 'A';
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.filledCircle((1.0 / 14.0) * ((2 * dropColumn) + 1), 
                                      (1.0 / 14.0) * (11 - (2 * row)), 1.0 / 17.0);
            } else {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.1, 0.92, 0.2, 0.05);
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.text(0.85, 0.92, "It is Red's turn");
                arrayBoard[row][dropColumn] = 'B';
                PennDraw.setPenColor(PennDraw.GREEN);
                PennDraw.filledCircle((1.0 / 14.0) * ((2 * dropColumn) + 1), 
                                      (1.0 / 14.0) * (11 - (2 * row)), 1.0 / 17.0);
            }
            moveNumber++;
        }
    }
    
    //insert disk in random location
    public void insertRandom() {
        PennDraw.setFontSize(12);
        double random = Math.random();
        int dropColumn = 0;
        for (int i = 0; i < width; i++) {
            if (i / 7.0 <= random && (i + 1) / 7.0 > random) {
                dropColumn = i;
                if (isColumnFull(dropColumn) == false) {
                    break;
                }
            }
        }
        int row = firstEmptySpace(dropColumn);
        arrayBoard[row][dropColumn] = 'B';
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledCircle((1.0 / 14.0) * ((2 * dropColumn) + 1), 
                              (1.0 / 14.0) * (11 - (2 * row)), 1.0 / 17.0);
        moveNumberAuto++;
        
    }
    
    //insert disk single player
    public void insertWithAuto(int dropColumn) {
        if (dropColumn < 0 || dropColumn > width - 1) {
            throw new RuntimeException("ERROR: Out of bounds column");
        }
        if (isColumnFull(dropColumn) == true) {
            //do nothing
        } else {
            int row = firstEmptySpace(dropColumn);
            arrayBoard[row][dropColumn] = 'A';
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.filledCircle((1.0 / 14.0) * ((2 * dropColumn) + 1), 
                                  (1.0 / 14.0) * (11 - (2 * row)), 1.0 / 17.0);
            moveNumberAuto++;
        }
    }
    
    //check for horizontal win
    public boolean checkHorizWin() {
        boolean horizWin = false; 
        String check = "";
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                check += arrayBoard[row][column];
                if (check.contains(redWins) == true) {
                    horizWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    horizWin = true;
                    winner = "Green";
                    break;
                }
            }
        }
        return horizWin;
    }
  
    //check for vertical win
    public boolean checkVertWin() {
        boolean vertWin = false;
        String check = "";
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                check += arrayBoard[row][column];
                if (check.contains(redWins) == true) {
                    vertWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    vertWin = true;
                    winner = "Green";
                    break;
                }
            }
        }
        return vertWin;
    }
    
    //check for diagonal win
    public boolean checkDiagWin() {
        boolean diagWin = false;
        String check = "";
        for (int i = 2; i < 6; i++) {
            check += arrayBoard[i][i - 2];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }  
        } 
        check = "";
        for (int a = 0; a < 4; a++) {
            check += arrayBoard[a][Math.abs(a - 3)];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }  
        } 
        check = "";
        for (int j = 0; j < 4; j++) {
            check += arrayBoard[j][j + 3];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int b = 0; b < 5; b++) {
            check += arrayBoard[b][Math.abs(b - 4)];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int m = 0; m < 6; m++) {
            check += arrayBoard[m][m];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int l = 1; l < 6; l++) {
            check += arrayBoard[l][l - 1];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int c = 0; c < 6; c++) {
            check += arrayBoard[c][Math.abs(c - 5)];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int n = 0; n < 6; n++) {
            check += arrayBoard[n][n + 1];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int d = 0; d < 6; d++) {
            check += arrayBoard[d][6 - d];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int e = 1; e < 6; e++) {
            check += arrayBoard[e][7 - e];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int o = 0; o < 5; o++) {
            check += arrayBoard[o][o + 2];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        check = "";
        for (int f = 2; f < 6; f++) {
            check += arrayBoard[f][8 - f];
                if (check.contains(redWins) == true) {
                    diagWin = true;
                    winner = "Red";
                    break;
                } else if (check.contains(greenWins) == true) {
                    diagWin = true;
                    winner = "Green";
                    break;
                }
        }
        return diagWin;
    }
    
    //checks for win in horizontal, vertical, and diagonal direction
    public boolean winCheck() {
        if (checkHorizWin() == true || checkVertWin() == true || 
            checkDiagWin() == true) {
            checkHorizWin();
            checkVertWin();
            checkDiagWin();
            return true;
        } else {
            return false;
        }
    }
    
    //check if game board is full with no winner
    public boolean isTie() {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (isColumnFull(i) == true) {
                count++;
            }
        }
        if (count == width) {
            return true;
        } else {
            return false;
        }
    }
    
    //click to insert disk
    public void clickInsert() {
        PennDraw.setFontSize(14);
        firstMove();
        boolean endGame = false;
        boolean clickedLastTime = false;
        boolean tie = false;
        while (true) {
            if (PennDraw.mousePressed()) {
                if (!clickedLastTime) {
                    double x = PennDraw.mouseX();
                    double y = PennDraw.mouseY();
                    
                    for (int i = 0; i < width; i++) {
                        if (i / 7.0 <= x && (i + 1) / 7.0 > x && y <= 6.0 / 7.0) {
                            insert(i);
                            tie = isTie();
                            endGame = winCheck();
                            break;
                        }
                    }
                }
                clickedLastTime = true;
            } else {
                clickedLastTime = false;
            }
            if (tie == true) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.9, 0.92, 0.2, 0.05);
                PennDraw.filledRectangle(0.1, 0.92, 0.2, 0.05);
                PennDraw.filledRectangle(0.5, 0.945, 0.20, 0.03);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.95, "It's a Tie!");
                PennDraw.setPenColor(229, 0, 255);
                PennDraw.rectangle(0.5, 0.89, 0.17, 0.02);
                PennDraw.setFontSize(12);
                PennDraw.text(0.5, 0.887, "Click to Return");
                break;
            }
            if (endGame == true) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.9, 0.92, 0.16, 0.05);
                PennDraw.filledRectangle(0.1, 0.92, 0.16, 0.05);
                PennDraw.filledRectangle(0.5, 0.945, 0.20, 0.03);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.95, "Team " + winner + " Wins!");
                PennDraw.setPenColor(229, 0, 255);
                PennDraw.rectangle(0.5, 0.89, 0.17, 0.02);
                PennDraw.setFontSize(12);
                PennDraw.text(0.5, 0.887, "Click to Return");
                break;
            }
        }
    }
    
    //click to insert disk, with auto
    public void clickInsertAuto() {
        PennDraw.setFontSize(15);
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.text(0.15, 0.92, "Comp is Green");
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.text(0.85, 0.92, "You are Red");
        firstMoveAuto();
        boolean endGame = false;
        boolean clickedLastTime = false;
        boolean tie = false;
        while (true) {
            if (moveNumberAuto % 2 == 0) {
                if (PennDraw.mousePressed()) {
                    if (!clickedLastTime) {
                        double x = PennDraw.mouseX();
                        double y = PennDraw.mouseY();
                        
                        for (int i = 0; i < width; i++) {
                            if (i / 7.0 <= x && (i + 1) / 7.0 > x && 
                                y <= 6.0 / 7.0) {
                                insertWithAuto(i);
                                tie = isTie();
                                endGame = winCheck();
                                break;
                            }
                        }
                    }
                    clickedLastTime = true;
                } else {
                    clickedLastTime = false;
                }
            } else {
                insertRandom();
                tie = isTie();
                endGame = winCheck();
            }
            if (tie == true) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.9, 0.92, 0.2, 0.05);
                PennDraw.filledRectangle(0.1, 0.92, 0.2, 0.05);
                PennDraw.filledRectangle(0.5, 0.945, 0.20, 0.03);
                PennDraw.setFontSize(20);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.95, "It's a Tie!");
                PennDraw.setPenColor(229, 0, 255);
                PennDraw.rectangle(0.5, 0.89, 0.17, 0.02);
                PennDraw.setFontSize(12);
                PennDraw.text(0.5, 0.887, "Click to Return");
                break;
            }
            if (endGame == true) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.filledRectangle(0.9, 0.92, 0.16, 0.05);
                PennDraw.filledRectangle(0.1, 0.92, 0.16, 0.05);
                PennDraw.filledRectangle(0.5, 0.945, 0.20, 0.03);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(0.5, 0.95, "Team " + winner + " Wins!");
                PennDraw.setPenColor(229, 0, 255);
                PennDraw.rectangle(0.5, 0.89, 0.17, 0.02);
                PennDraw.setFontSize(12);
                PennDraw.text(0.5, 0.887, "Click to Return");
                break;
            }
        }
    }
    
    //main testing
    public static void main(String[] args) {
        /**Board test = new Board();
        test.Insert();
                        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(test.arrayBoard[i][j] + " ");
            }
            System.out.println();
        }**/
    }
}
        
        

        
        
        
        