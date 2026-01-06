public class Fish {

        public String name;                //holds the name of the hero
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;


        public Fish(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =7;
            dy =-2;
            width = 100;
            height = 60;

        }

        public void move() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            if(ypos>450){
                dy=-dy;
            }
            if(ypos+height<250){
                dy = -dy;
            }
            if(xpos + width >1000){
                dx = -dx;
                width = -width;
            }
            if(xpos + width< 0){
                dx = -dx;
                width = -width;
            }
        }
    }


