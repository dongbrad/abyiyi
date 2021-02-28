/**
 * 
 */
package com.abyiyi.tools.zkclient;
 
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
 
/**
 * @author HUANGLIAO322
 *
 */
public class ZukServerHandler  extends SimpleChannelInboundHandler<Object> {
 
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("msg:"+msg);
		Channel channel = ctx.channel();
		channel.writeAndFlush("hi,"+msg+",i'm from server.");
	}
 
}