package model.modelImpl.enums;

import java.io.Serializable;

/**
 * 这里写上所有的接口的类型，相当于Web中的path
 */
public enum Actions implements Serializable {
    LOGIN,
    GET_INFO,
    ADD_COMPANY,
    GET_PRICE,//获取药品单价信息
    SAVE_BILLINFO//保存单据信息到服务器
}
