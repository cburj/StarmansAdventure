import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Meteor extends GameObject
{
	double dy = 1;	//Speed at which the meteor will travel vertically.
	
	public Meteor(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/meteorFull.png");
		update();
	}
	
	public void update()
	{
		y += dy;
		super.update();
	}

}
