package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidtest.Network.APIClient;
import com.example.androidtest.model.ActivitionList;
import com.example.androidtest.model.WorkList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    ActivitionAdapter adapter;
    ArrayList<WorkList> workArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        initView();
        initFunction();
    }

    private void initVariable() {
         workArrayList = new ArrayList<>();
    }

    private void initView() {

        recyclerView =  findViewById(R.id.rv_activision_list);

    }

    private void initFunction() {
        getActivitionData();
    }

    public void getActivitionData(){
        Call<List<ActivitionList>> call = APIClient.getInstance().getMyApi().getActivitionList("Activated","1");
        call.enqueue(new Callback<List<ActivitionList>>() {
            @Override
            public void onResponse(Call<List<ActivitionList>> call, Response<List<ActivitionList>> response) {
                if (response.code() == 200){
                    Log.d("response",""+response.body());
                    workArrayList.clear();
                   if (response.body() != null) {
                       workArrayList.addAll(response.body().get(0).getData().getData());
                   }
                   loadData();
                }
            }

            @Override
            public void onFailure(Call<List<ActivitionList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void loadData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActivitionAdapter(workArrayList);
       // adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}