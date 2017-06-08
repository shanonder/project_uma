package app.uma.net.socket;

	/**
	 * 此类由CodeGenerateUtil自动生成
	 */
import java.util.HashMap;
	public class DataHash{

	public static HashMap<Integer,String>Type2Class;
	public static HashMap<String,Integer>Class2Type;
	static{
		Type2Class = new HashMap<>();
		Type2Class.put(1, "app.uma.net.socket.data.AttributesData");
		Type2Class.put(2, "app.uma.net.socket.data.ItemData");
		Type2Class.put(3, "app.uma.net.socket.data.EquipData");
		Type2Class.put(4, "app.uma.net.socket.data.PackData");
		Type2Class.put(5, "app.uma.net.socket.data.GridData");
		Type2Class.put(6, "app.uma.net.socket.data.RoleData");

		Class2Type = new HashMap<>();
		Class2Type.put("app.uma.net.socket.data.AttributesData", 1);
		Class2Type.put("app.uma.net.socket.data.ItemData", 2);
		Class2Type.put("app.uma.net.socket.data.EquipData", 3);
		Class2Type.put("app.uma.net.socket.data.PackData", 4);
		Class2Type.put("app.uma.net.socket.data.GridData", 5);
		Class2Type.put("app.uma.net.socket.data.RoleData", 6);
	}

}