import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthItem extends GameObject
{
	double dy = 4;	//Speed at which the meteor will travel vertically.
	
	public HealthItem(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/health.png");
		update();
	}
	
	public void update()
	{
		y += dy;
		super.update();
	}

}
