import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Meteor extends GameObject
{
	double dy = 2;	//Speed at which the meteor will travel vertically.
	
	public Meteor(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		img = new Image("resources/Meteor.png");
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
	
	//Method which returns the position of the object as a string.
	public double getXPosition()
	{
		return x-20;
	}
	
	//Returns the X position of the GameObject
	public double getYPosition()
	{
		return y-20;
	}

}
