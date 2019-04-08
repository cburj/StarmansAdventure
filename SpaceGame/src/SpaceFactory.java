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
	
	AnimationTimer timer = new AnimationTimer() {

		@Override
		public void handle(long arg0) {
			//Game Loop Code Goes Here.
		}};
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
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
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		root.getChildren().add(canvas);
	}

}
