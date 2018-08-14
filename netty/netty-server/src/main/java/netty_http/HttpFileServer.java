package netty_http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/resources/netty";

    public static void main(String[] args) throws Exception{
        int port = 8080;
        if(args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String url = DEFAULT_URL;
        if(args.length > 1){
            url = args[1];
            new HttpFileServer().run(port,url);
        }
    }

    public void run(final int port,final String url) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                            ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
                        }
                    });
            ChannelFuture future = b.bind("localhost",port).sync();
            System.out.println("HTTP 文件目录服务器启动，网址是 ： " + "http://localhost :" + port + url);
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
