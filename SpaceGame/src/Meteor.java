import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Meteor extends GameObject
{
	double dx = 10;	//Speed at which a meteor will travel.
	
	public Meteor(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/meteor.png");
		update();
	}
	
	public void update()
	{
		x += dx;
		if(x>600 || x<0)
		{
			dx =-dx;
			y +=50;
		}
		super.update();
	}

}
