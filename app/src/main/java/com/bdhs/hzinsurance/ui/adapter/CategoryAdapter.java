package com.bdhs.hzinsurance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdhs.hzinsurance.R;


/**
 * 项目名称：Serialport
 * 类描述：
 * 创建人：kejian
 * 创建时间：2016-10-25 10:20
 * 修改人：Administrator
 * 修改时间：2016-10-25 10:20
 * 修改备注：
 */
public class CategoryAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Integer[] imgs = {
            R.mipmap.one, R.mipmap.two,R.mipmap.three,
            R.mipmap.four,R.mipmap.five,R.mipmap.six,
            R.mipmap.seven,R.mipmap.eight,R.mipmap.nine,
            R.mipmap.ten,R.mipmap.eleven
    };

    String[] str={"普外手术","骨科手术","泌尿手术","肝脏手术","母婴安康生育","心胸外科手术","肛肠手术","妇科手术","胆道胆囊手术","眼科手术","介入手术"};


    public CategoryAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.item_category,null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.tvCategory = (TextView) convertView.findViewById(R.id.tvCategory);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(imgs[position]);
        viewHolder.tvCategory.setText(str[position]);
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv;
        private TextView tvCategory;
    }
}
