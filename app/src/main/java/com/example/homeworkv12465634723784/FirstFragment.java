package com.example.homeworkv12465634723784;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;


public class FirstFragment extends Fragment {
    MyDataAdapter adapter;
    private final static String KEY_SIZE_OF_LIST = "key";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MyDataAdapter(DataSource.getInstance().getData());
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null){
            DataSource.getInstance().updateSize(savedInstanceState.getInt(KEY_SIZE_OF_LIST));
        }
        final View view = inflater.inflate(R.layout.fragment_first, container, false);
       // if (savedInstanceState == null){
            RecyclerView recyclerView = view.findViewById(R.id.list);
            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), DataSource.getSpanCountPortrait()));
            } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), DataSource.getSpanCountLandscape()));
            }
            final MyDataAdapter adapter = new MyDataAdapter(DataSource.getInstance().getData());
            recyclerView.setAdapter(adapter);

            Button addButton = view.findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSource.addItem();
                    adapter.notifyItemInserted(DataSource.getInstance().getData().size());
                }
            });
        //}
            /*else {
            if (adapter.getItemCount() < savedInstanceState.getInt(KEY_SIZE_OF_LIST)){
                DataSource.COUNT_OF_ITEMS = savedInstanceState.getInt(KEY_SIZE_OF_LIST);

            }
        }*/


        return view;
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        public List<DataSource.MyData> myData;

        public MyDataAdapter(List<DataSource.MyData> myData) {
            this.myData = myData;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.value.setText(myData.get(position).value + "");
            if(myData.get(position).value % 2 == 0) {
                holder.value.setTextColor(Color.RED);
            } else holder.value.setTextColor(Color.BLUE);
        }



        public String getItem(int position) {
            return myData.get(position).value + "";
        }

        @Override
        public int getItemCount() {
            return myData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView value;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.number_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.frag_second);
                    if (fragment == null){
                        fragment = new FragmentSecond();
                        Bundle bundle = new Bundle();
                        bundle.putString(FragmentSecond.KEY, adapter.getItem(getPosition()));
                        fragment.setArguments(bundle);
                    }
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SIZE_OF_LIST, DataSource.getInstance().getData().size());
    }



}
