package com.integrate.ssm.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的类
 * @Author guofei_wu
 * @Date 2017/8/23 16:24
 */
public class Msg {
    //返回100，表示成功; 返回200，表示失败
    private int code;
    //提示信息
    private String hintMsg;

    private Map<String,Object> extendm = new HashMap<String,Object>();


    public static Msg success(){
        Msg resultMsg = new Msg();
        resultMsg.setCode(100);
        resultMsg.setHintMsg("处理成功");
        return resultMsg;
    }

    public static Msg failed(){
        Msg resultMsg = new Msg();
        resultMsg.setCode(200);
        resultMsg.setHintMsg("处理失败");
        return resultMsg;
    }


    public Msg add(String key,Object value){
        this.getExtendm().put(key,value);
        return this;
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHintMsg() {
        return hintMsg;
    }

    public void setHintMsg(String hintMsg) {
        this.hintMsg = hintMsg;
    }

    public Map<String, Object> getExtendm() {
        return extendm;
    }

    public void setExtendm(Map<String, Object> extendm) {
        this.extendm = extendm;
    }
}
