import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
	
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	Random rand = new Random(System.currentTimeMillis());
	int count = 0;
	Factory factory;
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long arg0) {
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			for(GameObject obj : list)
			{
				obj.update();
			}
			factory = new Factory(gc);
			//list.add(factory.createProduct("nice", 0, 0));
			int size = 10;
			int spacing = 60;
			//Loop for creating a new row of meteors
			for(int i = 0; i <= size; i++)
			{
				Random spawnRand = new Random();
				int n = spawnRand.nextInt(2);
				n += 1;
				if(n%2 == 0)
				{
					list.add(factory.createProduct("meteor", spacing, 0));
				}
				spacing += 60;
			}
			//starting the game timer
			timer.start();
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
		
		//Instantiating the Factory
		factory = new Factory(gc);
		//list.add(factory.createProduct("nice", 0, 0));
		int size = 10;
		int spacing = 60;
		for(int i = 0; i <= size; i++)
		{
			Random spawnRand = new Random();
			int n = spawnRand.nextInt(2);
			n += 1;
			if(n%2 == 0)
			{
				list.add(factory.createProduct("meteor", spacing, 0));
			}
			spacing += 60;
		}
		//starting the game timer
		timer.start();
	}

}
