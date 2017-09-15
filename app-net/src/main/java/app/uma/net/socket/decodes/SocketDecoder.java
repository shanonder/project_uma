package app.uma.net.socket.decodes;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.net.socket.interfaces.MsgProtocol;




public class SocketDecoder extends CumulativeProtocolDecoder implements ProtocolDecoder {

	private static final Logger logger = LoggerFactory.getLogger(SocketDecoder.class);

	private ClientRequest clientRequest;


	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		return readData(session,in,out);
	}
	
	private boolean readData(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception{
		int remain = in.remaining();
		if(remain < MsgProtocol.headSize){
			return false;
		}
		in.mark();
		int h = in.getUnsignedShort();//2
		if(h != MsgProtocol.headFlag){
			logger.info("包结构异常");
			session.closeNow();
			return false;
		}
		
		
		@SuppressWarnings("unused")
		int v = in.getUnsignedShort();//2 version todo
		int l = in.getInt();//4
		int bl = l - MsgProtocol.headSize;
		int remains = in.remaining();
		if(remains > l - 9){ 
			byte[] bytes = new byte[bl];
			clientRequest = new ClientRequest(bytes);
			clientRequest.cmd = in.getInt();
			clientRequest.status = in.getUnsignedShort();
			clientRequest.testid = in.getInt();
			clientRequest.reserved = in.getInt();
			if(bl > 0){
				in.get(bytes, 0 , bl);
			}
			out.write(clientRequest);
			return true;
		}else{
			in.reset();
			return false;
		}
	}
}
