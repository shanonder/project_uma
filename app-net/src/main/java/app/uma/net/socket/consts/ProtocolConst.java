package app.uma.net.socket.consts;

	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:eaa4af61bb48a544939396e5cc2b4ad7
	 */
	public class ProtocolConst{
	/** 心跳 */
	public static int Heap  = 0x10000;
	/** 登录(gate) */
	public static int LoginGate  = 0x10001;
	/** 登录(game) */
	public static int LoginGame  = 0x10002;
	/** 创建角色 */
	public static int CreateRole  = 0x20001;
	/** 进入场景，推送角色的初始化信息 */
	public static int EnterWorld  = 0x30001;
	/** 进入场景，场景相关 */
	public static int EnterScene  = 0x30002;
	/** 创建背包 */
	public static int PackInit  = 0x40001;

}