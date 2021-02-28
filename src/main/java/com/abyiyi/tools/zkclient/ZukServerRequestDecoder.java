/**
 * 
 */
package com.abyiyi.tools.zkclient;
 
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
 
import java.util.List;
 
/**
 * @author HUANGLIAO322
 * 解析消息
 */
public class ZukServerRequestDecoder extends ByteToMessageDecoder{
	
 
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		
 
		System.out.println("ZukRequestDecoder.decode");
		
		if(buffer.readableBytes()>0){
			byte[] data = new byte[buffer.readableBytes()];
			buffer.readBytes(data);
			String str = new String(data);
			System.out.println("str:"+str);
			//解析出消息对象，继续往下面的handler传递
			out.add(str);
		}
		else
		{
			System.out.println("buffer is zero.");
		}
		
 
		
		//数据不完整，等待完整的数据包
		return ;
	}
}