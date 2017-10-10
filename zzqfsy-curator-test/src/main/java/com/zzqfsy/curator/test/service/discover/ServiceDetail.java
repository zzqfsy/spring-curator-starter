package com.zzqfsy.curator.test.service.discover;

public class ServiceDetail {
    public static final String REGISTER_ROOT_PATH = "/service";

    private String desc;
    private int weight;

    public ServiceDetail() {}
    public ServiceDetail(String desc, int weight) {
        this.desc = desc;
        this.weight = weight;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "ServiceDetail [desc=" + desc + ", weight=" + weight + "]";
    }
}
