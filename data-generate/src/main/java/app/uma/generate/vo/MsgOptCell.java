package app.uma.generate.vo;

import java.util.ArrayList;

import com.icday.builds.as.AsC2SBuilder;
import com.icday.builds.as.AsProtocolConstBuilder;
import com.icday.builds.as.AsS2CBuilder;
import com.icday.builds.java.JavaC2SBuilder;
import com.icday.builds.java.JavaProtocolConstBuilder;
import com.icday.builds.java.JavaS2CBuilder;
import com.icday.utils.CsvUtll;

import config.Version;

public class MsgOptCell {
	
	private static AsProtocolConstBuilder apcb = null;
	private static JavaProtocolConstBuilder jpcb = null;
	static {
		apcb = new AsProtocolConstBuilder("ProtocolConst", null, Version.version);
		jpcb = new JavaProtocolConstBuilder("ProtocolConst", null, Version.version);		
	}
	
	public static void frush(){
		apcb.frush();
		jpcb.frush();
	}
	

	public void operate() {
		String codeName = CsvUtll.upperFirestChar(name);
		new AsC2SBuilder(codeName + "Request",cmd, c2sCells,md5);
		new AsS2CBuilder(codeName + "Response",cmd,s2cCells,md5);
		new JavaC2SBuilder(codeName + "Request",cmd,c2sCells,md5);
		new JavaS2CBuilder(codeName + "Response",cmd, s2cCells,md5);
		apcb.addCmd(codeName, cmd, desc);
		jpcb.addCmd(codeName, cmd, desc);
	}
}
