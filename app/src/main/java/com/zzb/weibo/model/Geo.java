package com.zzb.weibo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 地理信息
 * Created by ZZB on 2015/8/30.
 */
public class Geo {

    @Expose
    public String longitude;//经度坐标
    @Expose
    public String latitude;//维度坐标
    @Expose
    public String city;//所在城市的城市代码
    @Expose
    public String province;//所在省份的省份代码
    @Expose
    @SerializedName("city_name")
    public String cityName;//所在城市的城市名称
    @Expose
    @SerializedName("province_name")
    public String provinceName;//所在省份的省份名称
    @Expose
    public String address;//所在的实际地址，可以为空
    @Expose
    public String pinyin;//地址的汉语拼音，不是所有情况都会返回该字段
    @Expose
    public String more;//更多信息，不是所有情况都会返回该字段
}
