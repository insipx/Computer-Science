// Draws text figures.
// This (third) version captures structure and eliminates redundancy.

public class DrawFigures3 {

    /** Draws a few figures: diamond-shaped, X-shaped, and rocket-shaped. 
    */
    public static void main(String[] args)
    {
        drawDiamond();
        System.out.println();

        drawX();
        System.out.println();

        drawRocket();
    }


    /** Draws a diamond-shaped figure.
    */
    public static void drawDiamond()
    {
        drawCone();
        drawV();
    }


    /** Draws an X-shaped figure.
    */
    public static void drawX()
    {
        drawV();
        drawCone();
    }


    /** Draws a rocket-shaped figure.
    */
    public static void drawRocket()
    {
        drawCone();
        drawBox();
        System.out.println("|United|");
        System.out.println("|States|");
        drawBox();
        drawCone();
    }


    /** Draws a box-shaped figure.
    */
    public static void drawBox()
    {
        System.out.println("+------+");
        System.out.println("|      |");
        System.out.println("|      |");
        System.out.println("+------+");
    }


    /** Draws a cone-shaped figure.
    */
    public static void drawCone()
    {
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /    \\");
    }


    /** Draws a V-shaped figure.
    */
    public static void drawV()
    {
        System.out.println(" \\    /");
        System.out.println("  \\  /");
        System.out.println("   \\/");
    }

}
