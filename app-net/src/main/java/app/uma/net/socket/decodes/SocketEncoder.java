package app.uma.net.socket.decodes;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import app.uma.net.socket.interfaces.ResponseMsg;

public class SocketEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ResponseMsg sp = (ResponseMsg)message;
		out.write(sp.entireMsg());
		sp.release();
		
	}

}
