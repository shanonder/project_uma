package app.uma.net.socket;

import java.io.IOException;

import app.uma.net.socket.decodes.ServerResponse;

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
