package com.bdhs.hzinsurance.entity;

import java.io.Serializable;

/**
 * 项目名称：qsinsurance
 * 类描述：
 * 创建人：kejian
 * 创建时间：2018-07-18 17:42
 * 修改人：Administrator
 * 修改时间：2018-07-18 17:42
 * 修改备注：
 */
public class EvaluateEntity implements Serializable {

    public String CommentID;
    public String UserID;
    public String PolicyID;
    public String ProductID;
    public String Star;
    public String Content;
    public String Reply;
    public String CreateTime;
    public String Mobile;
    public String WeiXinHeadImgUrl;

    @Override
    public String toString() {
        return "EvaluateEntity{" +
                "CommentID='" + CommentID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", PolicyID='" + PolicyID + '\'' +
                ", ProductID='" + ProductID + '\'' +
                ", Star='" + Star + '\'' +
                ", Content='" + Content + '\'' +
                ", Reply='" + Reply + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", WeiXinHeadImgUrl='" + WeiXinHeadImgUrl + '\'' +
                '}';
    }
}
