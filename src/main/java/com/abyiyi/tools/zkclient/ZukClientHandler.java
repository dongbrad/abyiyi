/**
 * 
 */
package com.abyiyi.tools.zkclient;
 
import com.alibaba.fastjson.JSON;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
/**
 *
 */
public class ZukClientHandler extends SimpleChannelInboundHandler<Object> {
 
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("ZukClientHandler.msg:"+JSON.toJSONString(msg));
	}
 
}