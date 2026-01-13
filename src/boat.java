import java.awt.*;

public class boat {


        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public boolean isAlive;
        public Rectangle hitbox;




        //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
        // if you put in a String, an int and an int the program will use this constructor instead of the one above.
        public boat(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =5;
            dy =0;
            width = 180;
            height = 100;
            isAlive = true;
            hitbox = new Rectangle(xpos, ypos, width, height);

        } // constructor

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            if (xpos > 1000-width){
                xpos = 0;
            }
            hitbox = new Rectangle(xpos, ypos, width, height);

        }
    }



