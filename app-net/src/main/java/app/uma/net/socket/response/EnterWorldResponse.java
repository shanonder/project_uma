package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import app.uma.net.socket.data.RoleData;
import java.util.ArrayList;
import app.uma.net.socket.data.PackData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class EnterWorldResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @role RoleData 角色
	 * @packs []PackData 背包数据
	 * @modules []short 开启模块
	 * @customize string 自定义保存参数
	 */
	public EnterWorldResponse(int status , RoleData role, ArrayList<PackData> packs, ArrayList<Integer> modules, String customize) throws Exception{
		super(0x50001 , status);
		output.writeData(role == null ? new Object() : role);
		output.writeArrayList(packs);
		output.writeArrayList(modules);
		output.writeUTF(customize == null ? "" : customize);

	}


}