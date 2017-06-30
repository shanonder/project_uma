package app.uma.world.astar;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * ClassName: AStar 
 * @Description: A星算法
 * @author kesar
 */
public class AStar
{
	public final static int BAR = 1; // 障碍值
	public final static int PATH = 2; // 路径
	public final static int DIRECT_VALUE = 10; // 横竖移动代价
	public final static int OBLIQUE_VALUE = 14; // 斜移动代价
	
	Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
	List<Node> closeList = new ArrayList<Node>();
	
	/**
	 * 开始算法
	 */
	public void start(MapInfo mapInfo ,Node sNode , Node end)
	{
		if(mapInfo==null) return;
		// clean
		openList.clear();
		closeList.clear();
		// 开始搜索
		openList.add(sNode);
		moveNodes(mapInfo , end);
	}
	

	/**
	 * 移动当前结点
	 */
	private void moveNodes(MapInfo mapInfo,Node end)
	{
		while (!openList.isEmpty())
		{
			if (isCoordInClose(end.point))
			{
				drawPath(mapInfo.maps, end);
				break;
			}
			Node current = openList.poll();
			closeList.add(current);
			addNeighborNodeInOpen(mapInfo,current , end);
		}
	}
	
	/**
	 * 在二维数组中绘制路径
	 */
	private void drawPath(int[][] maps, Node end)
	{
		if(end==null||maps==null) return;
//		System.out.println("总代价：" + end.G);
		while (end != null)
		{
			Point c = end.point;
			maps[c.y][c.x] = PATH;
			end = end.parent;
		}
	}

	/**
	 * 添加所有邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current , Node end)
	{
		int x = current.point.x;
		int y = current.point.y;
		// 左
		addNeighborNodeInOpen(mapInfo,current,end, x - 1, y, DIRECT_VALUE);
		// 上
		addNeighborNodeInOpen(mapInfo,current,end, x, y - 1, DIRECT_VALUE);
		// 右
		addNeighborNodeInOpen(mapInfo,current,end, x + 1, y, DIRECT_VALUE);
		// 下
		addNeighborNodeInOpen(mapInfo,current,end, x, y + 1, DIRECT_VALUE);
		// 左上
		addNeighborNodeInOpen(mapInfo,current,end, x - 1, y - 1, OBLIQUE_VALUE);
		// 右上
		addNeighborNodeInOpen(mapInfo,current,end, x + 1, y - 1, OBLIQUE_VALUE);
		// 右下
		addNeighborNodeInOpen(mapInfo,current,end, x + 1, y + 1, OBLIQUE_VALUE);
		// 左下
		addNeighborNodeInOpen(mapInfo,current,end, x - 1, y + 1, OBLIQUE_VALUE);
	}

	/**
	 * 添加一个邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current,Node end, int x, int y, int value)
	{
		if (canAddNodeToOpen(mapInfo,x, y))
		{
			Point point = new Point(x, y);
			int G = current.G + value; // 计算邻结点的G值
			Node child = findNodeInOpen(point);
			if (child == null)
			{
				int H=calcH(end.point,point); // 计算H值
				if(isEndNode(end.point,point))
				{
					child=end;
					child.parent=current;
					child.G=G;
					child.H=H;
				}
				else
				{
					child = new Node(point, current, G, H);
				}
				openList.add(child);
			}
			else if (child.G > G)
			{
				child.G = G;
				child.parent = current;
				openList.add(child);
			}
		}
	}

	/**
	 * 从Open列表中查找结点
	 */
	private Node findNodeInOpen(Point point)
	{
		if (point == null || openList.isEmpty()) return null;
		for (Node node : openList)
		{
			if (node.point.equals(point))
			{
				return node;
			}
		}
		return null;
	}


	/**
	 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
	 */
	private int calcH(Point end,Point point)
	{
		return Math.abs(end.x - point.x)
				+ Math.abs(end.y - point.y);
	}
	
	/**
	 * 判断结点是否是最终结点
	 */
	private boolean isEndNode(Point end,Point point)
	{
		return point != null && end.equals(point);
	}

	/**
	 * 判断结点能否放入Open列表
	 */
	private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
	{
		// 是否在地图中
		if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) return false;
		// 判断是否是不可通过的结点
		if (mapInfo.maps[y][x] == BAR) return false;
		// 判断结点是否存在close表
		if (isCoordInClose(x, y)) return false;

		return true;
	}

	/**
	 * 判断坐标是否在close表中
	 */
	private boolean isCoordInClose(Point point)
	{
		return point!=null&&isCoordInClose(point.x, point.y);
	}

	/**
	 * 判断坐标是否在close表中
	 */
	private boolean isCoordInClose(int x, int y)
	{
		if (closeList.isEmpty()) return false;
		for (Node node : closeList)
		{
			if (node.point.x == x && node.point.y == y)
			{
				return true;
			}
		}
		return false;
	}
}

