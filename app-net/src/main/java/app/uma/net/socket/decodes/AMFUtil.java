package app.uma.net.socket.decodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import app.uma.net.socket.datas.ItemData;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import flex.messaging.io.amf.Amf3Output;

public class AMFUtil {

	public static AMFUtil instance = new AMFUtil();
	
	private SerializationContext context;
	private Amf3Input amf3Input;
	private Amf3Output amfOut;
	AMFUtil(){
		context = new SerializationContext();
		context.legacyCollection = true;
//		context.supportRemoteClass = true;
		context.setSerializerClass(ItemData.class);
		amf3Input = new Amf3Input(context);
		amfOut = new Amf3Output(context);
	}
	
	public Object decodeObject(InputStream stream) throws Exception{
		amf3Input.reset();
		amf3Input.setInputStream(stream);
		Object object = amf3Input.readObject();
		amf3Input.close();
		return object;
	}

	public void encodeObject(DataOutputStream dataOut, Object data) throws IOException {
		if(data != null){
			DataOutputStream dataOutStream = dataOut; 
			amfOut.reset();
			amfOut.setOutputStream(dataOutStream);
			amfOut.writeObject(data);
			amfOut.close();
			
		}
	}
}
