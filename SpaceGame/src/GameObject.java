import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

abstract class GameObject
{
	protected Image img;
	protected double x,y;
	protected GraphicsContext gc;
	
	public GameObject(GraphicsContext gc, double x, double y)
	{
		this.gc = gc;
		this.x= x;
		this.y = y;
	}
	
	public void update()
	{
		if(img != null)
		{
			gc.drawImage(img, x, y, 60,60);	//Last 2 parameters indicated the size of the image in pixels.
		}
	}
	
	//Returns the X position of the GameObject
	public double getXPosition()
	{
		return x-20;
	}
	
	//Returns the Y position of the GameObject
	public double getYPosition()
	{
		return y-20;
	}
}
