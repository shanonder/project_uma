package app.uma.net.socket.decodes;

import java.io.IOException;

public class AMF3Response extends ServerResponse {

	public AMF3Response(int cmd, int status ,Object data) {
		super(cmd, status);
		try {
			output.writeAMFObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
