package com.zzb.weibo.model;

/**
 * 隐私设置
 * Created by ZZB on 2015/8/31.
 */
public class Privacy {
    @Expose
    public int comment;//是否可以评论我的微博，0：所有人、1：关注的人、2：可信用户
    @Expose
    public int geo;//是否开启地理信息，0：不开启、1：开启
    @Expose
    public int message;//是否可以给我发私信，0：所有人、1：我关注的人、2：可信用户
    @Expose
    public int realname;//是否可以通过真名搜索到我，0：不可以、1：可以
    @Expose
    public int badge;//勋章是否可见，0：不可见、1：可见
    @Expose
    public int mobile;//是否可以通过手机号码搜索到我，0：不可以、1：可以
    @Expose
    public int webim;//是否开启webim， 0：不开启、1：开启
}
