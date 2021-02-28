package com.abyiyi.tools.zkclient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
 
import com.alibaba.fastjson.JSON;

public class ZukClientRequestEncoder extends MessageToByteEncoder<Object>{
 
	@Override
	protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buffer) throws Exception {
 
		System.out.println("ZukResponseEncoder.encode:"+JSON.toJSON(obj));
		buffer.writeBytes(((String)obj).getBytes());
		
 
	}
}