package com.hejin.net.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hejin.net.R;

public class MainActivity extends AppCompatActivity implements MainContract.IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainPresenter presenter = new MainPresenter();

        presenter.requestNet();
    }
}
