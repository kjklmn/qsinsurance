package com.bdhs.hzinsurance.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：qsinsurance
 * 类描述：
 * 创建人：kejian
 * 创建时间：2018-07-18 17:48
 * 修改人：Administrator
 * 修改时间：2018-07-18 17:48
 * 修改备注：
 */
public class EvaluateBean implements Serializable{
    public int total;
    public int nice_ratio;
    public List<EvaluateEntity> lists;
}
