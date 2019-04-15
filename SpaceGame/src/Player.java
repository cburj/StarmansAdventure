import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player
{
	public Image image;
	public int x,y;
	GraphicsContext gc;
	int dx=10;
	int dy = 10;
	
	public Player(int x, int y, GraphicsContext gc)
	{
		this.x = x;
		this.y = y;
		this.gc = gc;
		image = new Image("resources/TESLA.png");
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
}