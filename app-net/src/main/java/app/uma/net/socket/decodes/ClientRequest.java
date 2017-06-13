package app.uma.net.socket.decodes;

import java.io.IOException;

import app.uma.net.socket.message.MsgBodyWrap;

/**
 * 使用byte数组作为缓冲区，使用{@link MsgBodyWrap}将byte数组转成合适的数据。
 * 
 * 
 */
public class ClientRequest{
	
	MsgBodyWrap msgBody = null;
	private int state;
	public int cmd;

	public int status;
	public int testid;
	public int reserved;

	public ClientRequest(byte[] array) throws IllegalArgumentException {
//		if (array == null) {
//			throw new IllegalArgumentException("消息缓冲区对象为null");
//		}
//		if (array.length == 0) {
//			throw new IllegalArgumentException("消息缓冲区对象大小为0");
//		}
		msgBody = MsgBodyWrap.newInstance4In(array);
	}

	public int getCmd() {
		return cmd;
	}

	public byte getByte() throws IOException {
		return msgBody.readByte();
	}

	public short getShort() throws IOException {
		return msgBody.readShort();
	}

	public int getInt() throws IOException {
		return msgBody.readInt();
	}

	public long getLong() throws IOException {
		return msgBody.readLong();
	}

	public float getFloat() throws IOException {
		return msgBody.readFloat();
	}

	public double getDouble() throws IOException {
		return msgBody.readDouble();
	}

	public String getString() throws IOException {
		return msgBody.readUTF();
	}
	
	public void relese(){
		if(msgBody!=null){
			msgBody.close();
		}
		msgBody=null;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}



}
