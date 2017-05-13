/**
 *
 * author: shanonder
 * date: 2017年5月6日 下午12:16:09
 *
 */
package app.uma.web;

import org.springframework.security.core.GrantedAuthority;

public class AppGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String url;
    private String method;

    public String getPermissionUrl() {
        return url;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.url = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public AppGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }
}