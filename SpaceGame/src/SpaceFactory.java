//Imports
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class SpaceFactory extends Application
{
	Pane root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	
	//Text and Text Effects:
	Text text = new Text();
	Text points = new Text();
	Text framesPerSec = new Text();
	Text health = new Text();
	DropShadow ds = new DropShadow();
	TextField answerBox;
	int startHealth = 100; //Health that the user starts off with.
	
	//Fields for Movement Strategies:
	MoveRightStrat mr = new MoveRightStrat();
	MoveLeftStrat ml = new MoveLeftStrat();
	
	//Fields for FPS Counter:
	int frames = 0;
	double prevMillis = 0;
	
	Player p1;
	
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
						System.out.println("turning right");
					}
					else if(event.getCode() == KeyCode.A)
					{
						gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
						p1.set_strategy(ml);
						p1.execute();
						System.out.println("turning left");
					}
					//When the user presses the enter key, their answer needs to be checked, and focus is given back to the player.
					else if(event.getCode() == KeyCode.ENTER)
					{
						root.requestFocus();
					}
				}
		
			};
			
	ArrayList<GameObject> list = new ArrayList<GameObject>();
	Random rand = new Random(System.currentTimeMillis());
	int count = 0;	//Used to count the number of rows of meteors
	Factory factory;
	//Loading the Question Master
	QuestionMaster qMast = new QuestionMaster();
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
				boolean qAsked = false;	//Boolean to indicate if a question has been asked in this current run through of spawning items.
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
						if(qAsked == false)	//If a question hasn't been asked when a health item spawns, a random Q is selected.
						{
							//qMast.randomQuestion();	//Prints a question to the console (OLD)
							text.setText(qMast.returnQuestion());	//Changes the question on screen to a new random one.
							
							//Sound Effect to Tell the User a New Question has been Asked.
							AudioClip audio = new AudioClip(getClass().getResource("/resources/question.wav").toExternalForm());
					        audio.setVolume(0.5f);
					        audio.play();
							qAsked = true;	//the value of qAsked is set to true to prevent multiple questions being asked at once.
						}
					}
					spacing += 100;
				}
			}
			count++;
			//FPS Counter Stuff - Needs Tidying up a bit.
			frames++;
			double fps = getFPS();
			int fpsINT = (int) fps;
			framesPerSec.setText("FPS: " + fpsINT);
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
		//Setting the scene.
		scene = new Scene(root, 500, 750);
		scene.getStylesheets().add("/resources/style.css");
		stage.setResizable(false);	//Prevents the user from resizing the game window.
		//stage.initStyle(StageStyle.UNDECORATED);	//Removes any window decorations.
		stage.setScene(scene);
		stage.setTitle("Starman's Space Adventure");
		stage.show();
		
		//Instantiating the Canvas
		canvas = new Canvas(600,800);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(46, 38, 79));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		root.getChildren().add(canvas);
		
		//Adding the player to the scene.
		p1 = new Player(150,500,gc);
		//gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		p1.set_strategy(mr);
		p1.execute();;
		scene.setOnKeyPressed(keyboardHandler);
		
		//Importing the Question File.
		qMast.importFile();
		
		//Drop Shadow Settings
		ds.setOffsetY(3.0f);
		ds.setColor(Color.BLACK);
		
		//Loading Custom Font:
		Font.loadFont(SpaceFactory.class.getResource("/resources/upheavtt.ttf").toExternalForm(),10);
		
		//Initialising the Question
		String stext = "Welcome to Starman's Space Adventure!";	//This is never seen by the user - unless there is a problem reading the questions from the file.
		text.getStyleClass().add("question");	//setting the class name
		text.setEffect(ds);
		text.setText(stext);
		text.setX(75);
		text.setY(75);
		text.setFont(Font.font("Upheaval TT (BRK)", FontWeight.THIN, FontPosture.REGULAR, 20));
		text.setWrappingWidth(370);
		text.setStyle("-fx-line-spacing: 0.5em;");
		text.setFill(Color.WHITE);
		text.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(text);
		
		//Initialising the User Points HUD Element.
		String pointText = "0 Points";
		points.setEffect(ds);
		points.setText(pointText);
		points.setX(-125);
		points.setY(745);
		points.setFont(Font.font("Upheaval TT (BRK)", FontWeight.THIN, FontPosture.REGULAR, 20));
		points.setWrappingWidth(370);
		points.setStyle("-fx-line-spacing: 1em;");
		points.setFill(Color.CYAN);
		points.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(points);
		
		//Initialising the FPS Counter HUD Element:
		String fpsText = "FPS: ";
		framesPerSec.setEffect(ds);
		framesPerSec.setText(fpsText);
		framesPerSec.setX(-140);
		framesPerSec.setY(25);
		framesPerSec.setFont(Font.font("Upheaval TT (BRK)", FontWeight.THIN, FontPosture.REGULAR, 20));
		framesPerSec.setWrappingWidth(370);
		framesPerSec.setStyle("-fx-line-spacing: 1em;");
		framesPerSec.setFill(Color.YELLOW);
		framesPerSec.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(framesPerSec);
		
		//Initialising the Health Points HUD Element:
		String healthText = "HEALTH: " + startHealth;
		health.setEffect(ds);
		health.setText(healthText);
		health.setX(255);
		health.setY(745);
		health.setFont(Font.font("Upheaval TT (BRK)", FontWeight.THIN, FontPosture.REGULAR, 20));
		health.setWrappingWidth(370);
		health.setStyle("-fx-line-spacing: 1em;");
		health.setFill(Color.rgb(255, 0, 195));
		health.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(health);
		
		//Initialising the Question Answer Box:
		answerBox = new TextField();
		answerBox.setPromptText("Answer Here");
		answerBox.setLayoutX(125);
		answerBox.setLayoutY(720);
		answerBox.setPrefWidth(250);
		answerBox.setFont(Font.font("Upheaval TT (BRK)", FontWeight.THIN, FontPosture.REGULAR, 20));
		answerBox.getStyleClass().add("answerBox");	//adds a class name to allow CSS Styling.
		root.getChildren().add(answerBox);
		root.requestFocus();
		
		//Background Music:
		int repeat = 50;	//Number of times the audio will be repeated, in this case 50x which will be much longer than anyone would play the game for.
        AudioClip audio = new AudioClip(getClass().getResource("/resources/audio.mp3").toExternalForm());
        audio.setVolume(0.5f);
        audio.setCycleCount(repeat);
        audio.play();
		
        //Starting the Animation Timer
  		timer.start();
	}
	
	//Method for calculating the FPS - for debugging and optimisation purposes.
	public double getFPS()
	{
		double deltaTime = System.currentTimeMillis() - prevMillis;
		double fps = 1/(deltaTime)*1000;	//as the time is received in milliseconds, the FPS has to be multiplied by 1000 - otherwise it would be in frames per millisecond
		prevMillis = System.currentTimeMillis();
		return fps;
	}

}
