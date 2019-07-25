/**
 * Name: Keshava Katti
 * PennKey: kattikes
 * Recitation: 212
 * 
 * Execute: java ConnectFour
 * 
 */

public class ConnectFour {
    public static void main(String[] args) {
        //create menu
        Menu mainMenu = new Menu();
        boolean clickedLastTime = false;
        
        while (true) {
            if (PennDraw.mousePressed()) {
                if (!clickedLastTime) {
                    double x = PennDraw.mouseX();
                    double y = PennDraw.mouseY();
                    
                    if (x > 0.2 && x < 0.7 && y > 0.54 && y < 0.66) {
                        Board boardGame = new Board();
                        boardGame.clickInsertAuto();
                    }
                    
                    if (x > 0.2 && x < 0.7 && y > 0.39 && y < 0.51) {
                        PennDraw.setFontSize(15);
                        Board boardGame = new Board();
                        boardGame.clickInsert();
                    }
                    
                    if (x > 0.33 && x < 0.67 && y > 0.87 && y < 0.91) {
                        Menu returnMenu = new Menu();
                    }
                    clickedLastTime = true;
                } else {
                    clickedLastTime = false;
                }
            }
        }
    }
}

