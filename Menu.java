/**
 * Name: Keshava Katti
 * PennKey: kattikes
 * Recitation: 212
 * 
 * Description: creates menu for connect 4
 * 
 */

public class Menu {
    //constructor
    public Menu() {
        PennDraw.clear(PennDraw.BLACK);
        PennDraw.setFontSize(35);
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.text(0.5, 0.8, "CONNECT 4: MAIN MENU");
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.rectangle(0.5, 0.6, 0.3, 0.06);
        PennDraw.text(0.5, 0.6, "One-Player Game");
        PennDraw.rectangle(0.5, 0.45, 0.3, 0.06);
        PennDraw.text(0.5, 0.45, "Two-Player Game");
        PennDraw.setPenColor(229, 0, 255);
        PennDraw.text(0.5, 0.2, "Click Option to Start");
    }
}
