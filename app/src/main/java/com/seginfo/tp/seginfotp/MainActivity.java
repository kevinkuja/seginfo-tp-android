package com.seginfo.tp.seginfotp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	private MyModel model;
	private MyView view;
	private MyController controller;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create MVC
        model = new MyModel();
        view = new MyView(this, model);
        controller = new MyController(this, model, view);
        
        // Bind controller to search button
        findViewById(R.id.clickId).setOnClickListener(controller);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    
}
