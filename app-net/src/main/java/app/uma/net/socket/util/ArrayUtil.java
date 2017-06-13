package app.uma.net.socket.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import app.uma.net.socket.DataHash;
import app.uma.net.socket.consts.DefaultTypeConst;
import app.uma.net.socket.enums.ClassTypeEnum;

public class ArrayUtil {
	

	public static final void write(DataOutputStream dataOut, ArrayList<?> item) throws Exception{
		if(item == null){
			dataOut.writeShort(-1);
			return;
		}
		dataOut.writeShort(item.size());
		for(int i=0 , len = item.size() ; i<len ; i++){
			Object ele = item.get(i);
			if(ele!=null){
				dataOut.writeShort(i);
			}else{
				continue;
			}
			Class<?> clazz= ele.getClass();
			int type = DataHash.Class2Type.get(clazz.getName());
			dataOut.writeShort(type);

			if(type == DefaultTypeConst.type_boolean){
				dataOut.writeBoolean((boolean) ele);
			}
			else if(type == DefaultTypeConst.type_int){
				dataOut.writeInt((int) ele);
			}
			else if(type == DefaultTypeConst.type_long){
				dataOut.writeLong((long) ele);
			}
			else if(type == DefaultTypeConst.type_short){
				dataOut.writeShort((short) ele);
			}
			else if(type == DefaultTypeConst.type_string){
				dataOut.writeUTF((String)ele);
			}else{
				Method method = clazz.getMethod("write", DataOutputStream.class, clazz);  
				method.invoke(null, dataOut , ele);
			}
		}
		dataOut.writeShort(-1);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final ArrayList read(DataInputStream input) throws Exception{
		int length = input.readShort();
		if(length == -1){
			return null;
		}
		ArrayList item = new ArrayList<>(length);
		int index = 0;
		int i;
		while((index = input.readShort()) != -1)
		{
			while(( i = item.size()) < index){
				item.add(i, null);
			}
			int type = input.readShort();
			if(type > 0){
				String className = DataHash.Type2Class.get(type);
				Class threadClazz = Class.forName(className);
				Method method = threadClazz.getMethod("read", DataInputStream.class,threadClazz);
				Object ele = method.invoke(null, input , threadClazz.newInstance());
				item.add(index, ele);
			}
			else if(type == ClassTypeEnum.t_boolean.getType()){
				item.add(index,input.readBoolean());
			}
			else if(type == ClassTypeEnum.t_short.getType()){
				item.add(index,input.readShort());
			}
			else if(type == ClassTypeEnum.t_int.getType()){
				item.add(index, input.readInt());
			}
			else if(type == ClassTypeEnum.t_long.getType()){
				item.add(index,input.readLong());
			}
			else if(type == ClassTypeEnum.t_string.getType()){
				item.add(index, input.readUTF());
			}
			else if(type == ClassTypeEnum.t_array.getType()){
				item.add(index,read(input));
			}
		}
		while(( i = item.size()) < length){
			item.add(i, null);
		}
		return item;
	}
}
