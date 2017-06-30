package app.uma.world.astar;
/**
 * 
 * @Description: 坐标
 */
public class Point
{

	public int x;
	public int y;

	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (obj instanceof Point)
		{
			Point c = (Point) obj;
			return x == c.x && y == c.y;
		}
		return false;
	}
}
