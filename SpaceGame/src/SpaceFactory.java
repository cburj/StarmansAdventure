//Imports
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SpaceFactory extends Application
{
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Player p1;
	MoveRightStrat mr = new MoveRightStrat();
	MoveLeftStrat ml = new MoveLeftStrat();
	
	//Controls for the Player:
	EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>()
			{

				@Override
				public void handle(KeyEvent event)
				{
					if(event.getCode() == KeyCode.D)
					{
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
						p1.set_strategy(mr);
						p1.execute();
						//System.out.println("turning right");
					}
					else if(event.getCode() == KeyCode.A)
					{
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
						p1.set_strategy(ml);
						p1.execute();
						//System.out.println("turning left");
					}
				}
		
			};
			
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	Random rand = new Random(System.currentTimeMillis());
	int count = 0;	//Used to count the number of rows of meteors
	Factory factory;
	//Game Loop
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long arg0) {
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			for(GameObject obj : list)
			{
				obj.update();
			}
			factory = new Factory(gc);
			int size = 8;
			int spacing = 20;
			//Loop for creating a new row of meteors
			if(count%60 == 0)	//The 60 represents approximately 1 seconds between each new row spawning.
			{
				for(int i = 0; i <= size; i++)
				{
					Random spawnRand = new Random();
					int n = spawnRand.nextInt(2);
					n += 1;
					//There is a 50/50 chance of a meteor spawning in each column.
					if(n%2 == 0)
					{
						list.add(factory.createProduct("meteor", spacing, -50));
					}
					//If no meteor has spawned, and the count is divisible by 7 then spawn medkits in any empty columns.
					if(count % 7 ==0 && n%2 !=0)
					{
						list.add(factory.createProduct("health", spacing, -50));
					}
					spacing += 100;
				}
			}
			count++;
		}};
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	//The start method deals with anything that needs to be initialised at the very beginning of the game.
	//E.g. backgrounds, canvases etc.
	public void start(Stage stage) throws Exception
	{
		root = new Pane();
		scene = new Scene(root, 600, 800);
		//stage.setResizable(false);	//Prevents the user from resizing the game window.
		stage.initStyle(StageStyle.UNDECORATED);	//Removes any window decorations.
		stage.setScene(scene);
		stage.setTitle("SpaceGame");
		stage.show();
		
		//Instantiating the Canvas
		canvas = new Canvas(600,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(46, 38, 79));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		root.getChildren().add(canvas);
		
		//Adding the player to the scene.
		p1 = new Player(150,500,gc);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		p1.set_strategy(mr);
		p1.execute();
		scene.setOnKeyPressed(keyboardHandler);
		
		timer.start();
	}

}
