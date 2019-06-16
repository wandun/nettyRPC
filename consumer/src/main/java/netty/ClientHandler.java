package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author GXM www.guokangjie.cn
 * @date 2019/6/15
 * 自定义一个客户端业务处理类
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    private Object response;
    public Object getResponse() {
        return response;
    }

    /**
     * 读取服务器端返回的数据(远程调用的结果)
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
        ctx.close();
    }
}