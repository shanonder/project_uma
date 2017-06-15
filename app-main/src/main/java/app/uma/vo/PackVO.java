package app.uma.vo;

import java.util.ArrayList;

import app.uma.dao.entity.Pack;
import app.uma.net.socket.data.GridData;
import app.uma.net.socket.data.PackData;

public class PackVO {
	private Pack db;

	public PackVO(Pack db) {
		this.db = db;
		
	}

	public Pack getDb() {
		return db;
	}
	
	public PackData toMsg(){
		PackData packData = new PackData();
		packData.setOpenLength(db.getOpenLenth());
		packData.setType(db.getType());
		ArrayList<GridData> gridDatas = new ArrayList<>();
		packData.setItemList(gridDatas);
		return packData;
	}

}
