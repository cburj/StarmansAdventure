import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends GameObject
{
	public Image image;
	public int x,y;
	GraphicsContext gc;
	int dx=10;
	int dy = 10;
	
	public Player(int x, int y, GraphicsContext gc)
	{
		super(gc, x, y);
		this.x = x;
		this.y = y;
		this.gc = gc;
		image = new Image("resources/TESLA.png");
		update();
	}
	
	private MovementStrat strategy;
	
	public void set_strategy(MovementStrat strategy)
	{
		this.strategy = strategy;
	}
	
	public void execute()
	{
		this.strategy.execute(this);
	}
	
	public void update()
	{
		super.update();
	}
	
	//Method which returns the x position of the object as a string.
	public double getXPosition()
	{
		return x;
	}
	
	//Method which returns the y position of the object as a string.
		public double getYPosition()
		{
			return y;
		}
}