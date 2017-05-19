package app.uma.net.socket.decodes;

import org.apache.mina.core.buffer.IoBuffer;

import app.uma.net.socket.interfaces.MsgProtocol;
import app.uma.net.socket.interfaces.ResponseMsg;
import app.uma.net.socket.message.MsgBodyWrap;

public class ServerResponse implements ResponseMsg {
	private int _cmd;
	private int _status = 200;
	public int testid;
	public int reserved;
	protected MsgBodyWrap output = MsgBodyWrap.newInstance4Out();
	
	public ServerResponse(int cmd, int status) {
		setCmd(cmd);
		setStatus(status);
	}
	@Override
	public void setCmd(int cmd) {
		this._cmd = cmd;
	}
	@Override
	public IoBuffer entireMsg() {
		byte[] body = output.toByteArray();
		int l = MsgProtocol.headSize + body.length;		
		IoBuffer buf = IoBuffer.allocate(l);
		buf.putUnsignedShort(MsgProtocol.headFlag);
		buf.putUnsignedShort(MsgProtocol.version);
		buf.putInt(l);
		buf.putInt(getCmd());
		buf.putUnsignedShort(getStatus());
		buf.putInt(testid);
		buf.putInt(reserved);
		buf.put(body);
		buf.flip();
		return buf;
	}
	@Override
	public void release() {
		if (output != null) {
			output.close();
		}
		output = null;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return _status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this._status = status;
	}
	/**
	 * @return the _cmd
	 */
	public int getCmd() {
		return _cmd;
	}
	
	
}
