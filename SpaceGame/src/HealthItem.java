import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthItem extends GameObject
{
	double dy = 1;	//Speed at which the meteor will travel vertically.
	
	public HealthItem(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/medkit.png");
		update();
	}
	
	public void update()
	{
		y += dy;
		if(y >= 750)
		{
			img = null;	//Removes the sprite once it reaches a certain depth on the screen, to increase performance.
		}
		super.update();
	}

}
