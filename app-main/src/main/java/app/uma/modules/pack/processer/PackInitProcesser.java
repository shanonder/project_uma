package app.uma.modules.pack.processer;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import app.uma.net.socket.data.PackData;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.PackInitRequest;
import app.uma.net.socket.response.PackInitResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.PackVO;

@Component
public class PackInitProcesser extends MsgProcessor {

	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		PackInitRequest request = new PackInitRequest(cr);
		ArrayList<PackVO> packVOs = gameSession.getPack(PackVO.class);
		if(packVOs == null || packVOs.size() == 0){
			return;
		}
		ArrayList<PackData> respPacks = new ArrayList<>();
		for(PackVO vo : packVOs){
			if(vo.getCfg().getType() == request.getType()){
				respPacks.add(vo.toMsg());
				break;
			}
		}
		gameSession.sendMsg(new PackInitResponse(200, respPacks));
	}

}
