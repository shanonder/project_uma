package app.uma.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.data.EquipData;
import app.uma.net.socket.data.ItemData;
import app.uma.net.socket.util.ArrayUtil;
import app.uma.net.socket.util.DataUtil;

public class DataTest {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
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
		File file = new File("C:/workspaces/datas/test/datatest.dat");
		
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputStream os = new FileOutputStream(file);
		DataOutputStream dataOut = new DataOutputStream(os);
		ArrayUtil.write(dataOut, objects);
		DataUtil.write(dataOut, null);
		DataUtil.write(dataOut, equipData);
		os.close();
		
		InputStream in = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(in);
		ArrayList<EquipData> newArr = ArrayUtil.read(dis);
		System.out.println(newArr.get(0).getClass().getName());
//		System.out.println(newArr.get(1).getClass().getName());
		System.out.println(newArr.get(2).getClass().getName());
		System.out.println(newArr.get(3).getClass().getName());
//		System.out.println(newArr.get(4).getClass().getName());
//		ArrayUtil.read(dis);
		ItemData itemData = DataUtil.read(dis);
		System.out.println(itemData != null?itemData.toString():"null");
		System.out.println(DataUtil.read(dis).toString());
	}
}
