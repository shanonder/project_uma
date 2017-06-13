package app.uma.net.socket.services;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.decodes.SocketCodeFactory;
import app.uma.net.socket.factorys.ServerThreadFactory;
import app.uma.net.socket.handlers.GameProtocolHandler;
import app.uma.net.socket.heaps.HeapProcesser;
import app.uma.net.socket.interfaces.IModuleFacade;
import app.uma.net.socket.message.MsgDispatcher;

@Service
public class GameService extends Thread {

	private int port = 3005;
	private NioSocketAcceptor acceptor;
	private OrderedThreadPoolExecutor threadpool;
	
	public GameService(int port){
		this.port = port;
	}
	
	@Autowired
	private IModuleFacade moduleFacade;
	
	public void start(){
		acceptor = new NioSocketAcceptor();
		acceptor.setBacklog(100);
		acceptor.setReuseAddress(true);
		
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SocketCodeFactory()));
		acceptor.setHandler(new GameProtocolHandler());
		threadpool = new OrderedThreadPoolExecutor(500);
		threadpool.setThreadFactory(new ServerThreadFactory("OrderedThreadPool"));
		chain.addLast("threadPool", new ExecutorFilter(threadpool));
		int recsize = 5120;
		int sendsize = 40480;                                                                                         
		int timeout = 60000;
		SocketSessionConfig sc = acceptor.getSessionConfig();
		sc.setReuseAddress(true);// 设置每一个非主监听连接的端口可以重用
		sc.setReceiveBufferSize(recsize);// 设置输入缓冲区的大小
		sc.setSendBufferSize(sendsize);// 设置输出缓冲区的大小
		sc.setTcpNoDelay(true);// flush函数的调用 设置为非延迟发送，为true则不组装成大包发送，收到东西马上发出   
		sc.setSoLinger(0);
		sc.setIdleTime(IdleStatus.READER_IDLE, timeout);
		try {
			acceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		moduleFacade.start();
	}

	
}
