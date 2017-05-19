package app.uma.net.socket.decodes;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SocketCodeFactory implements ProtocolCodecFactory {

	private final SocketEncoder encoder;
	private final SocketDecoder decoder;
	
	public SocketCodeFactory() {
		encoder = new SocketEncoder(); 
		decoder = new SocketDecoder();
	}
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
