package com.seginfo.tp.seginfotp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MyView extends View {

	private final Activity activity;
	private final MyModel model;
	
	private TextView counter;
	
	
	public MyView(Activity a, MyModel m) {
		super(a, null);
		
		activity = a;
		model = m;
		
        activity.setContentView(R.layout.activity_main);
        
        counter = (TextView) activity.findViewById(R.id.counterId);
	}
	
	public void setCounter() {
		counter.setText(model.getValue().toString());
	}
	
}
