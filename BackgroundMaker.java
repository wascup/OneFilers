import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BackgroundMaker {
    Scanner usrInput = new Scanner(System.in);
    Color bgColor = Color.decode("#009fb7");
    String colorName = "Pacific Blue";
    int screenWidth = 800;
    int screenHeight = 600;
    BufferedImage background;
    int boxSize = (int) (screenWidth / 5);
    public static void main(String[] args) {
        new BackgroundMaker();
    }
    public BackgroundMaker() {
        //Get user input
        usrInput.useDelimiter("\r?\n");
        System.out.print("Enter Width: ");
        screenWidth = usrInput.nextInt();
        System.out.print("Enter Height: ");
        screenHeight = usrInput.nextInt();
        background = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_RGB);
        System.out.print("Enter Hex Color: ");
        var color = usrInput.next().toString();
        if (!color.contains("#")) {
            color = "#" + color;
        }
        bgColor = Color.decode(color);
        System.out.print("Enter Colors Name: ");

        colorName = usrInput.next();

        //New graphics to draw on the image
        Graphics2D grafic = background.createGraphics();

        //Set the background to the color the user wants
        grafic.setBackground(bgColor);
        Color strokeColor;

        //Check if color is too dark or bright
        if (bgColor.getRed() > 127 && bgColor.getGreen() > 127 && bgColor.getBlue() > 127) {
            strokeColor = Color.black;
        } else {
            strokeColor = Color.white;
        }

        //Set stroke thickness based on how large your resolution is
        grafic.setStroke(new BasicStroke(screenHeight % 20));
        //Set stroke color based on color brightness
        grafic.setColor(strokeColor);

        grafic.clearRect(0, 0,screenWidth,screenHeight);
        grafic.drawRect((screenWidth / 2) - (boxSize /2),(screenHeight/2) - (boxSize/2), boxSize,boxSize);

        //Draw the first string which is the color name
        grafic.setFont(new Font(grafic.getFont().getFontName(), grafic.getFont().getStyle(), (boxSize/10)));
        grafic.drawString(colorName,(screenWidth / 2) - (boxSize /2) + (boxSize /2)/20, (int) ( (screenHeight/2) - (boxSize /2)*.75));

        //Draw the second string which is the hex color
        grafic.setFont(new Font(grafic.getFont().getFontName(), grafic.getFont().getStyle(), (boxSize/15)));
        grafic.drawString("#"+Integer.toHexString(bgColor.getRGB()).substring(2).toUpperCase(),(screenWidth / 2) - (boxSize /2) + (boxSize /2)/20,(int) ( (screenHeight/2) - (boxSize /2)*.60));

        //Save the file to your pc
        String fileName = colorName.replace(" ","-") + ".png";
        File newBG = new File(fileName);
        try {
            ImageIO.write(background, "png", newBG);
            System.out.println("Saved to: " + fileName);
        } catch (Exception e ) {
            System.out.println("ERROR: " + e);
            System.exit(-1);
        }
        System.exit(0);


    }
}