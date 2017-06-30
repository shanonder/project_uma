package app.uma.world;

import app.uma.world.astar.AStar;
import app.uma.world.astar.MapInfo;
import app.uma.world.astar.Node;

public class Test
{

	public static void main(String[] args)
	{
		int[][] maps = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } 
				};
		Node sn = new Node(1, 5);
		Node en = new Node(10, 5);
		MapInfo info=new MapInfo(maps,maps[0].length, maps.length);
		AStar aStar = new AStar();
		aStar.start(info , sn , en);
		printMap(maps);
	}
	
	/**
	 * 打印地图
	 */
	public static void printMap(int[][] maps)
	{
		for (int i = 0; i < maps.length; i++)
		{
			for (int j = 0; j < maps[i].length; j++)
			{
				System.out.print(maps[i][j] + " ");
			}
			System.out.println();
		}
	}
}
