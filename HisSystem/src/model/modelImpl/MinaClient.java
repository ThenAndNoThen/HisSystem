package model.modelImpl;


import com.alibaba.fastjson.JSON;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;


    public class MinaClient {
    private static Logger logger = Logger.getLogger(MinaClient.class);
    private static String HOST = "10.112.245.71";
    private static int PORT = 8888;
    private static IoConnector connector = new NioSocketConnector();
    private static IoSession session;

    public static IoConnector getConnector() {
        return connector;
    }

    public static void setConnector(IoConnector connector) {
        MinaClient.connector = connector;
    }
    
    public static void setHandler(IoHandler handler){
    	connector.setHandler(handler);
    }
    
    public static void startConnection() {
    	 // 设置链接超时时间
        connector.setConnectTimeout(30000);
        // 添加过滤器  可序列话的对象
        connector.getFilterChain().addLast(
                "codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        ConnectFuture future = connector.connect(new InetSocketAddress(
                HOST, PORT));// 创建连接
        future.awaitUninterruptibly();// 等待连接创建完成
        session = future.getSession();// 获得session
    }
    
    public static void sendRequest(RequestBody requestBody){
        try {
            session.write(JSON.toJSONString(requestBody));// 发送消息
            System.out.println("客户端发送消息成功，消息为:" + JSON.toJSONString(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!");
            logger.error("客户A端链接异常...", e);
        }
    }
}
