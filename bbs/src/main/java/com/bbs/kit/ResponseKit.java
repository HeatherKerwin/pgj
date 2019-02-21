package com.bbs.kit;

import com.bbs.Constant;
import com.blade.web.http.Response;

public class ResponseKit{

	public static void go(Response response,String path) {
		String ctx = Constant.SITE_URL;
		String location = fixPath(ctx, path);
		response.redirect(location);
	}

	public static String fixPath(String ctx,String path) {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if (ctx.length() > 1 && ctx.endsWith("/")) {
			ctx = ctx.substring(0, ctx.length() - 1);
		}
		return ctx + path;
	}
}