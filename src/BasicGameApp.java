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


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image Fishpic;
    public Image boatpic;
    public Image background;
    public Image octopic;
    public Image octobody;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
    private Fish Fish1;
    private boat boat1;
    private Octopus octo;
    private Octopus octohead;
    public int coinflip;



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}


	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		Fishpic = Toolkit.getDefaultToolkit().getImage("Fish.png"); //load the picture
        boatpic = Toolkit.getDefaultToolkit().getImage("boat.png"); //load the picture
        octopic = Toolkit.getDefaultToolkit().getImage("Octo.png"); //load the picture
        octobody = Toolkit.getDefaultToolkit().getImage("octobody.png"); //load the picture
        background = Toolkit.getDefaultToolkit().getImage("fishback.jpeg"); //load the picture
		Fish1 = new Fish((int)(Math.random() * 600),350);
        boat1 = new boat((int)(Math.random() * 400)+100, 100);
        octo = new Octopus((int)(Math.random() * 700)+100,750);
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
      //calls the move( ) code in the objects
		Fish1.move();
        boat1.move();
        octo.move();
        octo.height = octo.height + octo.dy;
        crashing();
        octohead.xpos = octo.xpos-25;
        octohead.ypos = octo.ypos+octo.height-90;
        octohead.hitbox.x = octohead.xpos;
        octohead.hitbox.y = octohead.ypos;

	}
    public void crashing(){
        if (boat1.hitbox.intersects(octohead.hitbox) && boat1.iscrashing == false){
            coinflip = (int)(Math.random() * 2);
            boat1.iscrashing = true;
            if(coinflip == 0){
                boat1.health = boat1.health-octo.strength;
                if(boat1.health<0){
                    boat1.isAlive = false;
                }
                System.out.println("boat is " + boat1.isAlive);
            }
            if(coinflip == 1){
               octohead.health = octohead.health-boat1.strength;
                if(octohead.health<0){
                    octohead.isAlive = false;
                }
                System.out.println("octo is " + octohead.isAlive);
            }

        }
        if(!boat1.hitbox.intersects(octohead.hitbox)){
            boat1.iscrashing = false;
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
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		g.drawImage(Fishpic, Fish1.xpos, Fish1.ypos, Fish1.width, Fish1.height, null);
        g.drawImage(boatpic, boat1.xpos, boat1.ypos, boat1.width, boat1.height, null);
        g.drawImage(octopic, octo.xpos, octo.ypos, octo.width, octo.height, null);
        if(octohead.isAlive == true) {
            g.drawImage(octobody, octohead.xpos, octohead.ypos, octohead.width, octohead.height, null);
        }
        g.setColor(Color.white);
        g.fillRect(boat1.xpos, boat1.ypos-20,boat1.totalhealth/2,20);
        g.fillRect(octohead.xpos, octohead.ypos-20,octohead.totalhealth/2,20);
        g.setColor(Color.red);
        g.fillRect(boat1.xpos, boat1.ypos-20,boat1.health/2,20);
        g.fillRect(octohead.xpos, octohead.ypos-20,octohead.health/2,20);
        g.setColor(Color.black);
        g.drawString("HEALTH: " + octohead.health + "/" + octohead.totalhealth, octohead.xpos, octohead.ypos-20);
        g.drawString("HEALTH: " + boat1.health + "/" + boat1.totalhealth, boat1.xpos, boat1.ypos-20);

		g.dispose();

		bufferStrategy.show();
	}
}