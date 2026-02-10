//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class BasicGameApp implements Runnable {

//all my variable I used
	final int WIDTH = 1000;
	final int HEIGHT = 700;
	public JFrame frame;
	public Canvas canvas;
    public JPanel panel;
	public BufferStrategy bufferStrategy;
    public Image boatpic;
    public Image background;
    public Image octopic;
    public Image octobody;
    public Image explosion;
    private Fish Fish1;
    private boat boat1;
    private Octopus octo;
    private Octopus octohead;
    private Healthpack snack;
    private Healthpack snack1;
    public int coinflip;




	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}


	public BasicGameApp() {
      
      setUpGraphics();
       

        boatpic = Toolkit.getDefaultToolkit().getImage("boat.png"); //load the picture
        octopic = Toolkit.getDefaultToolkit().getImage("Octo.png"); //load the picture
        octobody = Toolkit.getDefaultToolkit().getImage("octobody.png"); //load the picture
        background = Toolkit.getDefaultToolkit().getImage("fishback.jpeg"); //load the picture
        explosion = Toolkit.getDefaultToolkit().getImage("explosion.gif");
		Fish1 = new Fish((int)(Math.random() * 600),350);
        boat1 = new boat((int)(Math.random() * 400)+100, 100);
        octo = new Octopus((int)(Math.random() * 700)+100,750);
        snack = new Healthpack ((int)(Math.random() * 700)+100,(int) (Math.random()*400)+70, -2, 50);
        snack1 = new Healthpack ((int)(Math.random() * 700)+100, (int) (Math.random()*400)+70, 1,75);
        octohead = new Octopus (octo.xpos-25, octo.ypos+octo.height+10);
        octohead.width = 100;
        octohead.height = 100;
        octohead.hitbox.width = 100;
        octohead.hitbox.height = 100;
        Fish1.height = 90;
        Fish1.width = 150;


	}

	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the object
		Fish1.move();
        boat1.move();
        octo.move();
        snack.move();
        snack1.move();
        Fish1.shoot();
        //makes octopus character because it is made of two separate objects the head is separate from the body
        octo.height = octo.height + octo.dy;
        octohead.xpos = octo.xpos-25;
        octohead.ypos = octo.ypos+octo.height-90;
        octohead.hitbox.x = octohead.xpos;
        octohead.hitbox.y = octohead.ypos;

        //method that makes interactions
        crashing();

	}
    public void crashing(){

        //Ensures that the boat can only be hitting one thing at once
        if(!boat1.hitbox.intersects(octohead.hitbox) && !boat1.hitbox.intersects(snack1.hitbox) && !boat1.hitbox.intersects(snack.hitbox) && !boat1.hitbox.intersects(Fish1.shoothitbox)  && !boat1.hitbox.intersects(Fish1.hitbox)){
            boat1.iscrashing = false;
        }

        //Ensures that the octopus can only be hitting one thing at once
        if(!octohead.hitbox.intersects(boat1.hitbox) && !octohead.hitbox.intersects(snack.hitbox) && !octohead.hitbox.intersects(snack1.hitbox) && !octohead.hitbox.intersects(Fish1.shoothitbox)  && !octohead.hitbox.intersects(Fish1.hitbox)){
        octohead.iscrashing = false;
        }

        //Ensures that the fish can only be hitting one thing at once
        if(!Fish1.hitbox.intersects(octohead.hitbox) && !Fish1.hitbox.intersects(boat1.hitbox) && !Fish1.hitbox.intersects(snack1.hitbox) && !Fish1.hitbox.intersects(snack.hitbox)){
            Fish1.iscrashing = false;
        }

        //Ensures that the fish's projectiles can only be hitting one thing at once
        if(!Fish1.shoothitbox.intersects(octohead.hitbox) && !Fish1.shoothitbox.intersects(boat1.hitbox)){
            Fish1.isguncrashing = false;
        }


//intersection between boat and octopus, makes one of the two take damage.
        if (boat1.hitbox.intersects(octohead.hitbox) && boat1.iscrashing == false && octohead.iscrashing == false){
            coinflip = (int)(Math.random() * 2);
            boat1.iscrashing = true;
            octohead.iscrashing = true;
            //chooses if boat or octopus takes damage
            if(coinflip == 0){
                boat1.health = boat1.health-octohead.strength;
                if(boat1.health<=0){
                    boat1.isAlive = false;
                }
            }
            //same thing
            if(coinflip == 1){
               octohead.health = octohead.health-boat1.strength;
                if(octohead.health<=0){
                    octohead.isAlive = false;
                }
            }

        }

//intersection between boat and fish's projectile, makes boat take damage
        if(boat1.hitbox.intersects(Fish1.shoothitbox) && boat1.iscrashing == false && Fish1.isguncrashing == false){
            boat1.iscrashing = true;
            Fish1.isguncrashing = true;
                boat1.health = boat1.health-Fish1.strength;
                if(boat1.health<=0)
                {
                    boat1.isAlive = false;
                }

        }


//intersection between fish's projectile and octopus, makes octopus take damage.
        if(octohead.hitbox.intersects(Fish1.shoothitbox) && octohead.iscrashing == false && Fish1.isguncrashing == false){
            octohead.iscrashing = true;
            Fish1.iscrashing = true;
                octohead.health = octohead.health-Fish1.strength;
                if(octohead.health<=0)
                {
                    octohead.isAlive = false;
                    octo.hitbox = new Rectangle(-1000,-1000,1,1);
                    octohead.hitbox = new Rectangle(1200,-1000,1,1);
                }

        }

//intersection between fish and octopus, makes fish take damage.
        if(octohead.hitbox.intersects(Fish1.hitbox) && octohead.iscrashing == false && Fish1.iscrashing == false){
            octohead.iscrashing = true;
            Fish1.iscrashing = true;
            Fish1.health = Fish1.health-octohead.strength;
            if(Fish1.health<=0)
            {
                Fish1.isAlive = false;
            }

        }

//intersection between boat and fish, makes fish take damage.
        if(boat1.hitbox.intersects(Fish1.hitbox) && boat1.iscrashing == false && Fish1.iscrashing == false){
            boat1.iscrashing = true;
            Fish1.iscrashing = true;
                Fish1.health = Fish1.health-boat1.strength;
                if(Fish1.health<=0)
                {
                    Fish1.isAlive = false;
                }

        }

//intersection between boat and snack, heals and buffs boat.
        if(boat1.hitbox.intersects(snack.hitbox) && boat1.iscrashing == false)
        {
                boat1.health = boat1.health + snack.heal;
                boat1.strength = boat1.strength+snack.boost;
                boat1.iscrashing = true;

        }
//intersection between boat and snack, heals and buffs boat.
        if(boat1.hitbox.intersects(snack1.hitbox)&& boat1.iscrashing == false)
        {
            boat1.health = boat1.health + snack1.heal;
            boat1.strength = boat1.strength+snack1.boost;
            boat1.iscrashing = true;

        }

//intersection between octopus and snack, heals and buffs octopus.

        if(octohead.hitbox.intersects(snack.hitbox) && octohead.iscrashing == false)
        {
                octohead.health = octohead.health + snack.heal;
                octohead.strength = octohead.strength+snack.boost;
                octohead.iscrashing = true;


        }
//intersection between octopus and snack, heals and buffs octopus.
        if(octohead.hitbox.intersects(snack1.hitbox)&& octohead.iscrashing == false)
        {
            octohead.health = octohead.health + snack1.heal;
            octohead.strength = octohead.strength+snack1.boost;
            octohead.iscrashing = true;
        }


        //intersection between Fish and snack, heals and buffs Fish.
        if(Fish1.hitbox.intersects(snack.hitbox) && Fish1.iscrashing == false)
        {

                Fish1.health = Fish1.health + snack.heal;
                Fish1.strength = Fish1.strength+snack.boost;
                Fish1.iscrashing = true;
        }

        //intersection between Fish and snack, heals and buffs Fish.
        if(Fish1.hitbox.intersects(snack1.hitbox)&& Fish1.iscrashing == false)
        {

            Fish1.health = Fish1.health + snack1.heal;
            Fish1.strength = Fish1.strength+snack1.boost;
            Fish1.iscrashing = true;

        }

    }


	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        //makes background
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        //draws snack and displays it's stats

            g.setColor(Color.green);
            g.fillRect(snack.hitbox.x, snack.hitbox.y, snack.hitbox.width, snack.hitbox.height);
            g.setColor(Color.black);
            g.drawString("HEALS ON IMPACT: " + snack.heal, snack.xpos, snack.ypos-40);
            g.drawString("STRENGTH BOOST: " + snack.boost, snack.xpos, snack.ypos-20);
            g.drawRect(snack.hitbox.x, snack.hitbox.y, snack.hitbox.width, snack.hitbox.height);


        //draws snack and displays it's stats
            g.setColor(Color.green);
            g.fillRect(snack1.hitbox.x, snack1.hitbox.y, snack1.hitbox.width, snack1.hitbox.height);
            g.setColor(Color.black);
            g.drawString("HEALS ON IMPACT: " + snack1.heal, snack1.xpos, snack1.ypos-40);
            g.drawString("STRENGTH BOOST: " + snack1.boost, snack1.xpos, snack1.ypos-20);
            g.drawRect(snack1.hitbox.x, snack1.hitbox.y, snack1.hitbox.width, snack1.hitbox.height);



        //draws fish only if it alive and displays it's stats

        if (Fish1.isAlive == true) {
            g.setColor(Color.white);
            g.fillRect(Fish1.xpos, Fish1.ypos-20,Fish1.totalhealth/2,20);
            g.setColor(Color.red);
            g.fillRect(Fish1.xpos, Fish1.ypos-20, Fish1.health/2,20);
            g.setColor(Color.black);
            g.drawString("HEALTH: " + Fish1.health + "/" + Fish1.totalhealth, Fish1.xpos, Fish1.ypos-40);
            g.drawString("STRENGTH: " + Fish1.strength, Fish1.xpos, Fish1.ypos-20);
            g.fillRect(Fish1.shoothitbox.x, Fish1.shoothitbox.y, 20, 20);
            g.drawImage(Fish1.Fishpic, Fish1.xpos, Fish1.ypos, Fish1.width, Fish1.height, null);
            g.drawRect(Fish1.hitbox.x, Fish1.hitbox.y, Fish1.hitbox.width, Fish1.hitbox.height);

            //gives health above maximum from health pack a different color to show it had overheal;
            if (Fish1.health>boat1.totalhealth)
            {
                g.setColor(Color.CYAN);
                g.fillRect(Fish1.xpos+Fish1.totalhealth/2, Fish1.ypos-20,(Fish1.health- Fish1.totalhealth)/2,20);
            }

        }

        //draws boat only if it alive and displays it's stats
        if(boat1.isAlive == true){
            g.drawImage(boatpic, boat1.xpos, boat1.ypos, boat1.width, boat1.height, null);
            g.setColor(Color.white);
            g.fillRect(boat1.xpos, boat1.ypos-20,boat1.totalhealth/2,20);
            g.setColor(Color.red);
            g.fillRect(boat1.xpos, boat1.ypos-20,boat1.health/2,20);
            g.setColor(Color.black);
            g.drawString("HEALTH: " + boat1.health + "/" + boat1.totalhealth, boat1.xpos, boat1.ypos-40);
            g.drawString("STRENGTH: " + boat1.strength, boat1.xpos, boat1.ypos-20);
            g.drawRect(boat1.hitbox.x, boat1.hitbox.y, boat1.hitbox.width, boat1.hitbox.height);
            //gives health above maximum from health pack a different color to show it had overheal;
            if (boat1.health>boat1.totalhealth)
            {
                g.setColor(Color.CYAN);
                g.fillRect(boat1.xpos+boat1.totalhealth/2, boat1.ypos-20,(boat1.health- boat1.totalhealth)/2,20);
            }

        }

        //draws octopus only if it alive and displays it's stats
        if(octohead.isAlive == true) {
            g.drawImage(octopic, octo.xpos, octo.ypos, octo.width, octo.height, null);
            g.drawImage(octobody, octohead.xpos, octohead.ypos, octohead.width, octohead.height, null);
            g.setColor(Color.white);
            g.fillRect(octohead.xpos, octohead.ypos-20,octohead.totalhealth/2,20);
            g.setColor(Color.red);
            g.fillRect(octohead.xpos, octohead.ypos-20,octohead.health/2,20);
            g.setColor(Color.black);
            g.drawString("HEALTH: " + octohead.health + "/" + octohead.totalhealth , octohead.xpos, octohead.ypos-40);
            g.drawString("STRENGTH: " + octohead.strength, octohead.xpos, octohead.ypos-20);
            //gives health above maximum from health pack a different color to show it had overheal;

            if (octohead.health>octohead.totalhealth)
            {
                g.setColor(Color.CYAN);
                g.fillRect(octohead.xpos+octohead.totalhealth/2, octohead.ypos-20,(octohead.health- boat1.totalhealth)/2,20);
            }
            g.drawRect(octohead.hitbox.x, octohead.hitbox.y, octohead.hitbox.width, octohead.hitbox.height);
        }

        //makes win screen for octopus if it is the only one alive
        if (octohead.isAlive == true && boat1.isAlive ==false && Fish1.isAlive == false)
        {
            pause(2000);
            g.drawImage(explosion,0,0,1000,700,null);
            g.setColor(Color.white);
            g.drawString("Octopus Wins!!!!",475,350);
        }

        //makes win screen for boat if it is the only one alive
        if (octohead.isAlive == false && boat1.isAlive ==true && Fish1.isAlive == false)
        {
            pause(2000);
            g.drawImage(explosion,0,0,1000,700,null);
            g.setColor(Color.white);
            g.drawString("BOAT WINS!!!!",475,350);
        }

        //makes win screen for fish if it is the only one alive
        if (octohead.isAlive == false && boat1.isAlive ==false && Fish1.isAlive == true)
        {
            pause(2000);
            g.drawImage(explosion,0,0,1000,700,null);
            g.setColor(Color.white);
            g.drawString("FISH WINSs!!!!",475,350);
        }

		g.dispose();

		bufferStrategy.show();
	}
}