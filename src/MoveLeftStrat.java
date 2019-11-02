//Concrete Strategy for moving the player left on the screen.
public class MoveLeftStrat implements MovementStrat
{

	@Override
	public void execute(Player p)
	{
		p.x -= p.dx;
		p.gc.drawImage(p.image,p.x, p.y);
	}

}
