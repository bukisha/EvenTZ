package com.example.bookee.eventz.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Category;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MvpContract.View {
    private static final String TAG = "HomeActivity";
    private MvpContract.Presenter presenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starting");
        setContentView(R.layout.activity_home);
        listView=findViewById(R.id.category_list);
        presenter=new Presenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.fetchCategories();
    }

    @Override
    public void updateCategories(List<Category> categoryList) {
        ArrayList<String> categories=new ArrayList<>();
        for(Category c : categoryList) {
            categories.add(c.getName());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories);
        listView.setAdapter(adapter);
    }

    @Override
    public void displayErrorMessage(String s) {
        Toast.makeText(this, "Some error has happened "+s, Toast.LENGTH_LONG).show();
    }
}
