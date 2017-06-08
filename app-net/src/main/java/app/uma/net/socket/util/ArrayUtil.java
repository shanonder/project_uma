package app.uma.net.socket.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import app.uma.net.socket.DataHash;
import app.uma.net.socket.consts.DefaultTypeConst;
import app.uma.net.socket.message.MsgBodyWrap;

public class ArrayUtil {
	public static void writeCustom(MsgBodyWrap output, ArrayList<Object> item){
//		Object obj = item[1];
//		obj.getClass().write(output,obj);
	}
	
	public static void writeBasic(DataOutputStream dataOut, ArrayList<Object> item) throws Exception{
		if(item == null){
			dataOut.writeBoolean(false);
			return;
		}
		dataOut.writeShort(item.size());
		int size = dataOut.size();
		dataOut.writeShort(0);
		int count = 0;
		for (Object ele :item){ 
			if(ele != null){
				count ++;
				Class<?> clazz= ele.getClass();
				int type = DataHash.Class2Type.get(clazz.getName());
				if(type == DefaultTypeConst.type_byte){ 
					dataOut.writeByte((int) ele);
				}
				else if(type == DefaultTypeConst.type_boolean){
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
					Method method = clazz.getMethod("write", long.class);  
					method.invoke(null, dataOut , ele);
				}
				
			}
		}
//		dataOut.write(count, size, 2);
	}
	
	
	public static ArrayList<Object> read(DataInputStream input) throws Exception{
		if(!input.readBoolean()){
			return null;
		}
		ArrayList<Object> item = new ArrayList<>(input.readShort());
		int itemCount = input.readShort();
		for(int i = 0 ; i < itemCount ; ++i){
			int index = input.readShort();
			int type = input.readShort();
			if(type == 0){ //ArrayList
				ArrayList<Object> ele = read(input);
				item.set(index, ele);
			}
			if(type > 0){
				String className = DataHash.Type2Class.get(type);
				Class<?> threadClazz = Class.forName(className);  
				Method method = threadClazz.getMethod("read", long.class);  
				item.set(index, method.invoke(null, input , null));
			}
			
			if(type == DefaultTypeConst.type_byte){ 
				item.set(index,input.readByte());
			}
			if(type == DefaultTypeConst.type_boolean){
				item.set(index,input.readBoolean());
			}
			if(type == DefaultTypeConst.type_int){
				item.set(index,input.readInt());
			}
			if(type == DefaultTypeConst.type_long){
				item.set(index,input.readLong());
			}
			if(type == DefaultTypeConst.type_short){
				item.set(index,input.readShort());
			}
			if(type == DefaultTypeConst.type_long){
				item.set(index,input.readUTF());
			}
		}
		return item;
	}
}
