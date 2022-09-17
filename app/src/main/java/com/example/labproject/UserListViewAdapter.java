package com.example.labproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<User> listUser;

    UserListViewAdapter(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listUser.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.product_view, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        User user = (User) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.idproduct)).setText(String.format("ID = %d", user.getId()));
        ((TextView) viewProduct.findViewById(R.id.nameproduct)).setText(String.format("Name : %s", user.getName()));
        ((TextView) viewProduct.findViewById(R.id.priceproduct)).setText(String.format("Email: %s", user.getEmail()));

        return viewProduct;
    }
}
