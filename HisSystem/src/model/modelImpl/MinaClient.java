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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;


    public class MinaClient {
    private static Logger logger = Logger.getLogger(MinaClient.class);
//    private static String HOST = "10.112.144.45";
    private static String HOST = "127.0.0.1";
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
    	InputStream inputStream;
    	Properties p = new Properties(); 
		try {
			inputStream = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
			p.load(inputStream);
			HOST = p.getProperty("ip");
			PORT = Integer.valueOf(p.getProperty("port"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("ip设置文件加载失败，使用默认设置，localhost：8888");
		}   
		
		try {
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
		}catch(Exception e) {
			logger.error("与服务器建立链接失败！");
		}
    }
    
    public static void sendRequest(RequestBody requestBody){
        try {
            session.write(JSON.toJSONString(requestBody));// 发送消息
            logger.info("客户端发送消息成功，消息为:" + JSON.toJSONString(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!");
            logger.error("客户A端链接异常...", e);
        }
    }
}
