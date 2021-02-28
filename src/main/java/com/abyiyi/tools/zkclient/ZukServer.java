/**
 * 
 */
package com.abyiyi.tools.zkclient;
 
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
 
public class ZukServer {
	
	public static void main(String[] args) {
		
		ServerBootstrap server = new ServerBootstrap();
		try {
			EventLoopGroup parentGroup = new NioEventLoopGroup();
			EventLoopGroup childGroup = new NioEventLoopGroup();
			server.group(parentGroup, childGroup);
			
			server.channel(NioServerSocketChannel.class);
			
			server.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					//接受消息解码
					ch.pipeline().addLast(new ZukServerRequestDecoder());
					//消息处理
					ch.pipeline().addLast(new ZukServerHandler());
					//返回消息编码
					ch.pipeline().addLast(new ZukServerResponseEncoder());
					
				}
			});
			
			server.option(ChannelOption.SO_BACKLOG, 2048);// 链接缓冲池队列大小
			server.bind(10102).sync();
			
			System.out.println("server started.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
 
}