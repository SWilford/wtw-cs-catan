import javax.swing.JFrame;

public class catanDriver {
        public static catanGraphics screen;					//Game window


        public static void main(String[]args) {
            screen = new catanGraphics();
            JFrame frame = new JFrame("catan");    //window title
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocation(100, 50);                //location of game window on the screen
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(screen);
            frame.setVisible(true);
        }
        }
