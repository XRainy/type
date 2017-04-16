package com.type.pojo.enumeration;

/**
 * @Author : dx
 * @Date : 2017/4/7
 * Description :描述订单状态
 */
public enum TypeUserState {
    UNACTIVE("unActive",1),//未通过邮箱激活;
    //AVTIVE("ACTIVE",2),//邮箱已经激活；
    OFFLINE("offline",2),//用户离线；
    ONLINE("online",3);//用户在线；

    private String key;
    private int value;

    private TypeUserState(String key,int value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
