package app.uma.net.socket.responses;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由protocol_generate_util自动生成
 */
public class EnterSceneResponse  extends ServerResponse{
	public EnterSceneResponse(int status , int sceneId, double posX, double posY) throws Exception{
		super(0x30002 , status);
		output.writeInt(sceneId);
		output.writeDouble(posX);
		output.writeDouble(posY);

	}


}