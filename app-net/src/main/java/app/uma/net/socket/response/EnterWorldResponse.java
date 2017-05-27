package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.data.RoleData;
import java.util.ArrayList;
import app.uma.net.socket.data.]PackData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class EnterWorldResponse  extends ServerResponse{
	public EnterWorldResponse(int status , RoleData role, ArrayList<]PackData> packs, ArrayList<]Short> modules, String customize) throws Exception{
		super(0x30001 , status);
		output.writeAMFObject(role == null ? new Object() : role);
		output.writeAMFObject(packs == null ? new ArrayList<>() : packs);
		output.writeAMFObject(modules == null ? new ArrayList<>() : modules);
		output.writeUTF(customize == null ? "" : customize);

	}


}