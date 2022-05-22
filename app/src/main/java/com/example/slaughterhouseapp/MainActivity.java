package com.example.slaughterhouseapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<RequestResponse> {

    private Button btnBuy, btnGetAll;
    private RecyclerView recycler;
    private CustomAdapter adapter;
    private ProgressDialog pDialog;
    private Api api;
    private List<ChickenAsset> chickensList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuy = findViewById(R.id.btn_buy);
        btnGetAll = findViewById(R.id.btn_getAll);
        recycler = findViewById(R.id.recycler);
        btnBuy.setOnClickListener(this);
        btnGetAll.setOnClickListener(this);
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading...");
        api = RetrofitClientInstance
                .getRetrofitInstance()
                .create(Api.class);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy: {
                pDialog.show();
                for(int i = 0 ; i < chickensList.size() ; i++) {
                    Call<RequestResponse> call = api.updateChicken(
                            new RequestBody(
                                    chickensList.get(i).getChickenId(),
                                    chickensList.get(i).getChickenFarm(),
                                    chickensList.get(i).getFeedingCompany(),
                                    chickensList.get(i).getMotherCompany(),
                                    "Baharan Protein",
                                    "Baharan Protein"
                            )
                    );
                    call.enqueue(this);
                }

                break;
            }
            case R.id.btn_getAll: {
                pDialog.show();
                Call<List<ChickenAsset>> call = api.getAllChickens();
                call.enqueue(new Callback<List<ChickenAsset>>() {
                    @Override
                    public void onResponse(Call<List<ChickenAsset>> call, Response<List<ChickenAsset>> response) {
                        pDialog.dismiss();
                        generateDataList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<ChickenAsset>> call, Throwable t) {
                        pDialog.dismiss();
                        String err = "Something went wrong...Please try later!";
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            }
        }
    }

    @Override
    public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {
        pDialog.dismiss();
        Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<RequestResponse> call, Throwable t) {
        pDialog.dismiss();
        String err = "Something went wrong...Please try later!";
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void generateDataList(List<ChickenAsset> datList) {
        chickensList = datList;
        adapter = new CustomAdapter(this, datList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

    }

}