package noman.smart.manager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

public class serviceManager extends Service implements SensorEventListener {
	
	private SensorManager mySensorManager;
	private Sensor myAccelerometer,myProximity;
	float acValue=0 , proValue=1 ;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	   public int onStartCommand(Intent intent, int flags, int startId) {
	      
		   mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		   
		   myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		   myProximity = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		   mySensorManager.registerListener(this,  myAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
		   mySensorManager.registerListener(this, myProximity, SensorManager.SENSOR_DELAY_NORMAL);
		   
		   return START_STICKY;
	   }

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	   public void onDestroy() {
	      super.onDestroy();
	      Toast.makeText(this, "Manager Stopped", Toast.LENGTH_LONG).show();
	   }

	  public void onSensorChanged(SensorEvent sensorEvent) {
		// TODO Auto-generated method stub
		  
		  Sensor mySensor = sensorEvent.sensor;
		        
		  if (mySensor.getType() == Sensor.TYPE_PROXIMITY) 
		  {	
		        /*if (sensorEvent.values[0] == 0) 
		        {
		        	AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
		        	audioManager.setRingerMode(audioManager.RINGER_MODE_SILENT);
		        } 
		        else 
		        {
		        	AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
		        	audioManager.setRingerMode(audioManager.RINGER_MODE_NORMAL);
		        }*/
			  
			   proValue = sensorEvent.values[0];
		  }
		  
		  if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) 
		  {
			  acValue = sensorEvent.values[2];
		  }
		  
		  if( proValue == 0 && acValue < -8.4 )
		  {
			  AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
	          audioManager.setRingerMode(audioManager.RINGER_MODE_SILENT);
		  }
		  
		  else
		  {
			  AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
	          audioManager.setRingerMode(audioManager.RINGER_MODE_NORMAL);
		  }
	  }
}
