package app.uma.net.websocket;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.cnblogs.com/pctzhang/archive/2012/02/19/2358496.html
 * @author amu
 *
 */
public class WebSocketIoHandler extends IoHandlerAdapter {

    public static final String INDEX_KEY = WebSocketIoHandler.class.getName() + ".INDEX";
    private static Logger LOGGER = LoggerFactory.getLogger(WebSocketIoHandler.class);
    
    Map<Long, IoSession> ioSessionMap = new HashMap<Long, IoSession>();
    
    public void messageReceived(IoSession session, Object message) throws Exception {
    	IoBuffer buffer = (IoBuffer)message;
    	
    	byte[] b = new byte[buffer.limit()];  
    	buffer.get(b); 

    	Long sid = session.getId();

    	if (!ioSessionMap.containsKey(sid)) {
    		LOGGER.info("user '{}',has been created" + sid);
    		ioSessionMap.put(sid, session);
    		
        	String m = new String(buffer.array());
			String sss = getSecWebSocketAccept(m);
			
			buffer.clear();
			buffer.put(sss.getBytes("utf-8"));
			
			buffer.flip();
			session.write(buffer);
			buffer.free();
    	} else {
    		String m = decode(b);
    		LOGGER.info("from client is :" + m);
        	buffer.clear();

        	byte[] bb = encode(m);

        	buffer.put(bb);
        	buffer.flip();
        	
        	synchronized (ioSessionMap) {
            	Collection<IoSession> ioSessionSet = ioSessionMap.values();
            	for (IoSession is : ioSessionSet) {
    				if (is.isConnected()) {
    					System.out.println("response message to " + is);
    					is.write(buffer.duplicate());
    				}
        		}
            }
    		buffer.free();
    	}
    }

    @Override   
    public void sessionOpened(IoSession session) throws Exception {
        session.setAttribute(INDEX_KEY, 0);
    }

    @Override   
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception {   
        System.out.println( "IDLE " + session.getIdleCount( status ));   
    } 
    
	public static String getSecWebSocketAccept(String key) {
		String secKey = getSecWebSocketKey(key);

		String guid = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		secKey += guid;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(secKey.getBytes("iso-8859-1"), 0, secKey.length());
			byte[] sha1Hash = md.digest();
			secKey = base64Encode(sha1Hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String rtn = "HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept: "
				+ secKey + "\r\n\r\n";
		return rtn;
	}
	
	public static String getSecWebSocketKey(String req) {
		Pattern p = Pattern.compile("^(Sec-WebSocket-Key:).+",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher m = p.matcher(req);
		if (m.find()) {
			String foundstring = m.group();
			return foundstring.split(":")[1].trim();
		} else {
			return null;
		}

	}


	public static String base64Encode(byte[] input) {
		return new String(org.apache.mina.util.Base64.encodeBase64(input));
	}

	// / <summary>
	// /判断传入数据是否存在掩码
	// / 传入数据：hi
	// / socket接收到的二进制数据:
	// / 1000000110000010 1101011011101001
	// / 111110 111000 10111110 10000000
	// / 掩码异或的操作:
	// / 111110 111000 10111110 10000000
	// / 进行异或^ 111110 111001 11010110 11101001
	// / 结果: 1101000 1101001
	// / 数据样例:
	// / [0] 129 byte
	// / [1] 130 byte
	// / [2] 214 byte
	// / [3] 233 byte
	// / [4] 62 byte
	// / [5] 56 byte
	// / [6] 190 byte
	// / [7] 128 byte
	// / </summary>
	// / <returns></returns>
	public static String decode(byte[] receivedDataBuffer)
			throws UnsupportedEncodingException {
		String result = null;

		// 计算非空位置
		int lastStation = receivedDataBuffer.length - 1;
		
		// 利用掩码对org-data进行异或
		int frame_masking_key = 1;
		for (int i = 6; i <= lastStation; i++) {
			frame_masking_key = i % 4;
			frame_masking_key = frame_masking_key == 0 ? 4 : frame_masking_key;
			frame_masking_key = frame_masking_key == 1 ? 5 : frame_masking_key;
			receivedDataBuffer[i] = (byte) (receivedDataBuffer[i] ^ receivedDataBuffer[frame_masking_key]);
		}

		result = new String(receivedDataBuffer, 6, lastStation - 5, "UTF-8");

		return result;

	}

	// / 对传入数据进行无掩码转换
	public static byte[] encode(String msg) throws UnsupportedEncodingException {
		// 掩码开始位置
		int masking_key_startIndex = 2;

		byte[] msgByte = msg.getBytes("UTF-8");

		// 计算掩码开始位置
		if (msgByte.length <= 125) {
			masking_key_startIndex = 2;
		} else if (msgByte.length > 65536) {
			masking_key_startIndex = 10;
		} else if (msgByte.length > 125) {
			masking_key_startIndex = 4;
		}

		// 创建返回数据
		byte[] result = new byte[msgByte.length + masking_key_startIndex];

		// 开始计算ws-frame
		// frame-fin + frame-rsv1 + frame-rsv2 + frame-rsv3 + frame-opcode
		result[0] = (byte) 0x81; // 129

		// frame-masked+frame-payload-length
		// 从第9个字节开始是 1111101=125,掩码是第3-第6个数据
		// 从第9个字节开始是 1111110>=126,掩码是第5-第8个数据
		if (msgByte.length <= 125) {
			result[1] = (byte) (msgByte.length);
		} else if (msgByte.length > 65536) {
			result[1] = 0x7F; // 127
		} else if (msgByte.length > 125) {
			result[1] = 0x7E; // 126
			result[2] = (byte) (msgByte.length >> 8);
			result[3] = (byte) (msgByte.length % 256);
		}

		// 将数据编码放到最后
		for (int i = 0; i < msgByte.length; i++) {
			result[i + masking_key_startIndex] = msgByte[i];
		}
		
		return result;
	}
}
