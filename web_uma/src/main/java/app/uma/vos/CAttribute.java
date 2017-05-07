/**
 *
 * author: shanonder
 * date: 2017年5月6日 下午6:07:30
 *
 */
package app.uma.vos;

import org.springframework.security.access.ConfigAttribute;

public class CAttribute implements ConfigAttribute{
	
	private static final long serialVersionUID = 1L;
	private String  attribute;
	@Override
	public String getAttribute() {
		return attribute;
	}

}
