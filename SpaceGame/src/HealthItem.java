import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HealthItem extends GameObject
{
	double dx = 0;	//Speed at which a meteor will travel horizontally.
	double dy = 4;	//Speed at which the meteor will travel vertically.
	
	public HealthItem(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/health.png");
		update();
	}
	
	public void update()
	{
		x += dx;
		y += dy;
		if(x>600 || x<0)
		{
			dx =-dx;
			y +=50;
		}
		super.update();
	}

}
