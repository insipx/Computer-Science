// Draws text figures.
// This (second) version captures structure but does not eliminate redundancy.

public class DrawFigures2 {

    /** Draws a few figures: diamond-shaped, X-shaped, and rocket-shaped.
    */
    public static void main(String[] args) {

        drawDiamond();
        System.out.println();

        drawX();
        System.out.println();

        drawRocket();
    }
    

    /** Draws a diamond-shaped figure.
    */
    public static void drawDiamond() {
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
        System.out.println(" \\    /");
        System.out.println("  \\  /");
        System.out.println("   \\/");
    }
    

    /** Draws an X-shaped figure.
    */
    public static void drawX() {
        System.out.println(" \\    /");
        System.out.println("  \\  /");
        System.out.println("   \\/");
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
    }
    

    /** Draws a rocket-shaped figure.
    */
    public static void drawRocket() {
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
        System.out.println("+------+");
        System.out.println("|      |");
        System.out.println("|      |");
        System.out.println("+------+");
        System.out.println("|United|");
        System.out.println("|States|");
        System.out.println("+------+");
        System.out.println("|      |");
        System.out.println("|      |");
        System.out.println("+------+");
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
    }

}
