package com.example.allen.coor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Allen on 2017/5/7.
 */

public class RecyclerViewFragment extends Fragment {

    private AppCompatActivity mActivity;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private GroupAdapter mRecyclerAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
//        View view = inflater.inflate(R.layout.content_scrolling, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerAdapter = new GroupAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private class GroupAdapter extends RecyclerView.Adapter<ViewHolder> {

        public GroupAdapter(RecyclerViewFragment recyclerViewFragment) {

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textview.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;

        public ViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
