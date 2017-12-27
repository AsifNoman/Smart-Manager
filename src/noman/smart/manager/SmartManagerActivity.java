package noman.smart.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SmartManagerActivity extends Activity {
    
	Button start,stop;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        start = (Button) findViewById(R.id.button1);
        stop = (Button) findViewById(R.id.button2);
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		start.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startService(new Intent(getBaseContext(), serviceManager.class));
			}
		});
		
       stop.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				stopService(new Intent(getBaseContext(), serviceManager.class));
				
			}
		});
		
	}
    
    
}