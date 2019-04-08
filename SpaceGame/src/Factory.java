import javafx.scene.canvas.GraphicsContext;

public class Factory implements FactoryIF
{

	GraphicsContext gc;
	
	public Factory(GraphicsContext gc)
	{
		super();
		this.gc = gc;
	}
	
	@Override
	public GameObject createProduct(String discrim, double x, double y)
	{
		if(discrim.contentEquals("meteor"))
		{
			return new Meteor(gc, x, y);
		}
		else if(discrim.equals("health"))
		{
			return new HealthItem(gc, x, y);
		}
		return null;
	}

}
