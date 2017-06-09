/**
 *
 * author: shanonder
 * date: 2017年6月10日 下午2:11:38
 *
 */
package app.uma.net.socket.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;

import app.uma.net.socket.DataHash;

public class DataUtil {
	
	public static final <E> void write(DataOutputStream out,E data) throws Exception{
		if(data == null){
			out.writeBoolean(false);
			return;
		}
		out.writeBoolean(true);
		Class<?> clazz = data.getClass();
		out.writeShort(DataHash.Class2Type.get(clazz));
		Method method = clazz.getMethod("write", DataOutputStream.class, clazz);  
		method.invoke(null, out , data);
	}
	
	@SuppressWarnings("unchecked")
	public static final <E> E read(DataInputStream in) throws Exception{
		boolean notNull = in.readBoolean();
		if(notNull == false){
			return null;
		}
		String className = DataHash.Type2Class.get(in.readShort());
		Class<?> threadClazz = Class.forName(className);
		Method method = threadClazz.getMethod("read", DataInputStream.class,threadClazz);
		E data = (E) method.invoke(null, in , threadClazz.newInstance());
		return data;
	}
}
