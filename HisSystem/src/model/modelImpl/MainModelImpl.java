package model.modelImpl;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dto.BillDTO;
import dto.UnitPriceDTO;
import model.MainModel;
import model.modelImpl.enums.Actions;
import presenter.MainPresenterCallBack;

public class MainModelImpl extends IoHandlerAdapter implements MainModel   {
	private static Logger logger = Logger.getLogger(MainModelImpl.class);
	MainPresenterCallBack dataReceivedCallBack;
	public MainModelImpl() {
		MinaClient.setHandler(this);
		MinaClient.startConnection();
	}

	@Override
	public void setDataCallBack(MainPresenterCallBack dataReceivedCallBack) {
		// TODO Auto-generated method stub
		this.dataReceivedCallBack = dataReceivedCallBack;
	}

	@Override
	public void getUnitPrice(String unitName) {
		// TODO Auto-generated method stub
		RequestBody req = new RequestBody();
		req.setAction(Actions.GET_PRICE);
		req.setData(unitName);
		MinaClient.sendRequest(req);
	}

	@Override
	public void saveBillInfo(BillDTO bill) {
		// TODO Auto-generated method stub
		RequestBody req = new RequestBody();
		req.setAction(Actions.SAVE_BILLINFO);
		req.setData(bill);
		MinaClient.sendRequest(req);
	}
	
	@Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String msg = message.toString();
        logger.info("客户端接收到数据:" + msg);
        ResponseBody responseBody = JSON.parseObject(message.toString(), ResponseBody.class);
        logger.info("客户端收到消息raw："+msg + ", 解析后：" +responseBody);
        if(responseBody.getCode() != 0) {
        	dataReceivedCallBack.messageFailed(responseBody.getMsg());
        	return;
        }
        switch(responseBody.getAction()) {
        	case GET_PRICE:
        		JSONObject js = (JSONObject) responseBody.getData();
        		UnitPriceDTO up = js.toJavaObject(UnitPriceDTO.class);
        		dataReceivedCallBack.unitPriceRecived(up);
        		break;
        	case SAVE_BILLINFO:
        		dataReceivedCallBack.messageSuccessed(responseBody.getMsg());
        		break;
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        logger.error("发生错误...", cause);
    }
	
}
