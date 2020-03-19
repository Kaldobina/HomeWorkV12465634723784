package com.example.homeworkv12465634723784;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static MyDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new FirstFragment())
                    .commit();
        }
        adapter = new MyDataAdapter(DataSource.getInstance().getData());

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int position = 0; position < DataSource.getCountOfItems(); position++){
            outState.putString(position + "", adapter.getItem(position));
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int position = 0; position < DataSource.getCountOfItems(); position++){
            savedInstanceState.getString(position + "", adapter.getItem(position));
        }
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
                    FragmentManager fm = getSupportFragmentManager();
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

}
