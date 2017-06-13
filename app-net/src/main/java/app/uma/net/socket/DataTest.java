package app.uma.net.socket;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
<<<<<<< HEAD
=======
import java.util.ArrayList;

import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.data.EquipData;
import app.uma.net.socket.data.ItemData;
import app.uma.net.socket.util.ArrayUtil;
import app.uma.net.socket.util.DataUtil;
>>>>>>> branch 'master' of https://github.com/shanonder/project_uma.git

public class DataTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
<<<<<<< HEAD
//		EquipData equipData = new EquipData();
		
//		ArrayList<EquipData> objects = new ArrayList<>();
//		objects.add(equipData);
//		objects.add(null);
//		objects.add(equipData);
//		objects.add(equipData);
=======
		EquipData equipData = new EquipData();
		equipData.setInsId("12312312");
		ArrayList<AttributesData> attris = new ArrayList<>();
		AttributesData ad = new AttributesData();
		ad.setCfgId(1);
		ad.setValue(2);
		attris.add(ad);
		equipData.setAttributes(attris);
		ArrayList<EquipData> objects = new ArrayList<>();
		objects.add(equipData);
		objects.add(null);
		objects.add(equipData);
		objects.add(equipData);
>>>>>>> branch 'master' of https://github.com/shanonder/project_uma.git
		File file = new File("C:/workspaces/datas/test/datatest.dat");
		
		OutputStream os = new FileOutputStream(file);
<<<<<<< HEAD
		DataOutputStream out = new DataOutputStream(os);
		out.writeDouble(-1111111L);
		out.writeLong(1111111L);
		out.writeLong(-1231231231231L);
=======
		DataOutputStream dataOut = new DataOutputStream(os);
		ArrayUtil.write(dataOut, objects);
		DataUtil.write(dataOut, null);
		DataUtil.write(dataOut, equipData);
>>>>>>> branch 'master' of https://github.com/shanonder/project_uma.git
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
//		ArrayUtil.read(dis);
		ItemData itemData = DataUtil.read(dis);
		System.out.println(itemData != null?itemData.toString():"null");
		System.out.println(DataUtil.read(dis).toString());
	}
}
