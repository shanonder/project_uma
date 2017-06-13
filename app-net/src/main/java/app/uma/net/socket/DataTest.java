package app.uma.net.socket;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DataTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
//		EquipData equipData = new EquipData();
		
//		ArrayList<EquipData> objects = new ArrayList<>();
//		objects.add(equipData);
//		objects.add(null);
//		objects.add(equipData);
//		objects.add(equipData);
		File file = new File("C:/workspaces/datas/test/datatest.dat");
		
		OutputStream os = new FileOutputStream(file);
		DataOutputStream out = new DataOutputStream(os);
		out.writeDouble(-1111111L);
		out.writeLong(1111111L);
		out.writeLong(-1231231231231L);
		os.close();
//		System.out.println(objects.get(0).getClass().getName());
////		System.out.println(objects.get(1).getClass().getName());
//		System.out.println(objects.get(2).getClass().getName());
//		System.out.println(objects.get(3).getClass().getName());
////		System.out.println(objects.get(4).getClass().getName());
//		
//		out.writeBoolean(true);
//		ArrayUtil.write(out, objects);
//		EquipData.write(out, null);
//		ArrayUtil.write(dataOut, objects);
//		os.close();
//		
//		InputStream in = new FileInputStream(file);
//		DataInputStream dis = new DataInputStream(in);
//		ArrayList<EquipData> newArr = ArrayUtil.read(dis);
//		System.out.println(newArr.get(0).getClass().getName());
////		System.out.println(newArr.get(1).getClass().getName());
//		System.out.println(newArr.get(2).getClass().getName());
//		System.out.println(newArr.get(3).getClass().getName());
//		System.out.println(newArr.get(4).getClass().getName());
		
	}
}
