package app.uma.net.socket.interfaces;

/**
 * 消息协议相关常量
 * @author dyz
 *
 */
public interface MsgProtocol {

	/**默认flag值*/
	public int headFlag = 0xFFFF;
	
	public int headSize = 22;

	public int version = 0x0001;
}
