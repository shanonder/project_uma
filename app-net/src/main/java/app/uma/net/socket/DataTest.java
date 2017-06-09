package app.uma.net.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import app.uma.net.socket.data.EquipData;
import app.uma.net.socket.data.ItemData;
import app.uma.net.socket.util.ArrayUtil;

public class DataTest {
	
	public static void main(String[] args) throws Exception{
		EquipData equipData = new EquipData();
		ArrayList<ItemData> items = new ArrayList<>();
		ArrayList<Object> objects = new ArrayList<>();
		objects.add(1);
		objects.add(new Byte((byte) 0));
		objects.add("asdasd");
		objects.add(objects);
		objects.add(equipData);
		
		File file = new File("C:/workspaces/datas/test/datatest.dat");
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputStream os = new FileOutputStream(file);
		DataOutputStream dataOut = new DataOutputStream(bos);
		System.out.println(objects.get(0).getClass().getName());
		System.out.println(objects.get(1).getClass().getName());
		System.out.println(objects.get(2).getClass().getName());
		System.out.println(objects.get(3).getClass().getName());
		System.out.println(objects.get(4).getClass().getName());
		ArrayUtil.write(dataOut, items);
		os.close();
		
	}
}
