package app.uma.net.socket.services;

import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.message.MsgProcessor;

/**
 * 消息处理器注册类，所有的消息处理器，都在此注册实例化
 *
 */
public enum UserMsgRegister {
	/**用户登录*/
	login(ProtocolConst.LoginResponse,new LoginProcesser())
	;
	
	
	private int msgCode;
	private MsgProcessor processor;
	private UserMsgRegister(int msgCode,MsgProcessor processor){
		this.msgCode = msgCode;
		this.processor = processor;
	}
	
	public int getMsgCode(){
		return this.msgCode;
	}
	
	public MsgProcessor getMsgProcessor(){
		return this.processor;
	}
}
