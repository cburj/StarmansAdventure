//Concrete Strategy for moving the player right on the screen.
public class MoveRightStrat implements MovementStrat
{

	@Override
	public void execute(Player p)
	{
		p.x += p.dx;
		p.gc.drawImage(p.image,p.x, p.y);
	}

}
