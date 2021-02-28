/**
 * 
 */
package com.abyiyi.tools.zkclient;
 
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
 
import com.alibaba.fastjson.JSON;
 
/**
 * @author HUANGLIAO322
 *
 */
public class ZukServerResponseEncoder extends MessageToByteEncoder<Object>{
 
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf buffer)
			throws Exception {
		System.out.println("ZukResponseEncoder.encode:"+JSON.toJSON(msg));
		buffer.writeBytes(((String)msg).getBytes());
 
	} 
	
}