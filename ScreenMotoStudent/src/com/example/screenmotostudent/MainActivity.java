package com.example.screenmotostudent;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import BaseDatos.BaseDatos;
import EnviarArchivo.SocketEnvio;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
    private Button insertarRPM;
    public TextView rpmActual, velocidad, temperatura;
	public static TextView textRxUSB;
    private ImageView imagenModo;
    private EditText textoRpm;
    private ToggleButton tb;
    private Button enviar;
    String rpmActualtext;
    int numerito;
    int rpm = 7499;
    private final String TAG = MainActivity.class.getSimpleName(); 
    
    RelativeLayout layout1;
    public static BaseDatos datosBd ;
    public static String dispositivo;
    //------------------USB------------------------
    private UsbManager myUsbManager;
	private UsbAccessory myUsbAccessory;
	private ParcelFileDescriptor myParcelFileDescriptor;
	private FileInputStream myFileInputStream;
	private FileOutputStream myFileOutputStream;

	 

    @Override
    @SuppressWarnings("deprecation")
  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
       
		//-----------------USB--------------------------------------------
        myUsbManager = UsbManager.getInstance(this);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		registerReceiver(myUsbReceiver, intentFilter);
		
		//-------------------FIN USB------------------------------------
		// generarDatos();
        velocidad = (TextView) findViewById(R.id.velocidad);
        temperatura = (TextView)findViewById(R.id.temperatura);
        insertarRPM = (Button)findViewById(R.id.insertarRpm);
        rpmActual = (TextView)findViewById(R.id.rmpActual);
        textoRpm = (EditText)findViewById(R.id.editText);
        textRxUSB=(TextView)findViewById(R.id.RXusb);
        imagenModo = (ImageView) findViewById(R.id.imageView);
        layout1 = (RelativeLayout) findViewById(R.id.layout);
        layout1.setBackgroundColor(Color.GREEN);
        datosBd=new BaseDatos(this,"DBmotostudent",null,1);
        SQLiteDatabase baseDatos=datosBd.getWritableDatabase();
		baseDatos.execSQL("DELETE FROM "+ "tablaDatos"+";");
        miThread();
        miThread2();

        enviar = (Button)findViewById(R.id.button1);
		 //Button press event listener
		 enviar.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			SocketEnvio socket=new SocketEnvio();
			Thread t1=new Thread(socket);
			t1.start();
			 
			}
		});

        /*String resultado = Settings.Global.getString(getContentResolver(), Settings.Global.AIRPLANE_MODE_ON);

        if(Integer.parseInt(resultado) == 0){
            imagenModo.setImageResource(R.drawable.ic_action_network_cell);

        }else{
            imagenModo.setImageResource(R.drawable.ic_action_airplane_mode_off);

        }
        Toast.makeText(this, "result:"+resultado, Toast.LENGTH_SHORT).show();*/

        tb=(ToggleButton)findViewById(R.id.toggleButton);  //Getting the Toggle Button Reference
        //Next few lines of Code for setting the status of Toggle Button(CHECKED or UNCHECKED) based on Current AIRPLANE Mode.

        //Getting the State of AirPlane Mode
        int status=Settings.System.getInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0);
        if(status==1) //Means Airplane mode is ON
        {
            tb.setChecked(true);  //setting toggle Button as Checked
            Toast.makeText(getApplicationContext(), "Air Plane Mode is On",Toast.LENGTH_LONG).show();//Displaying Message to User
        }
        else  //if Airplane mode is OFF
        {
            tb.setChecked(false);//setting toggle Button as Unchecked
            Toast.makeText(getApplicationContext(), "Air Plane Mode is Off",Toast.LENGTH_LONG).show();//Displaying Message to User
        }
        
        
        
    }


    //Called every time when toggle button clicked
    @SuppressWarnings("deprecation")
    public void changeAirPlaneStatus(View v)
    {
        if(tb.isChecked())//means this is the request to turn ON AIRPLANE mode
        {
            Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 1);//Turning ON Airplane mode.
            Toast.makeText(getApplicationContext(), "Air Plane Mode is On",Toast.LENGTH_LONG).show();//Displaying a Message to user
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);//creating intent and Specifying action for AIRPLANE mode.
            intent.putExtra("state", true);////indicate the "state" of airplane mode is changed to ON
            sendBroadcast(intent);//Broadcasting and Intent

        }
        else//means this is the request to turn OFF AIRPLANE mode
        {
            Settings.System.putInt(getContentResolver(),Settings.System.AIRPLANE_MODE_ON, 0);//Turning OFF Airplane mode.
            Toast.makeText(getApplicationContext(), "Air Plane Mode is Off",Toast.LENGTH_LONG).show();//Displaying a Message to user
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);//creating intent and Specifying action for AIRPLANE mode.
            intent.putExtra("state", false);//indicate the "state" of airplane mode is changed to OFF
            sendBroadcast(intent);//Broadcasting and Intent
        }
    }


    /*
    *Trheads para lanzar la velocidad temperatura y cambio de revoluciones
    */


    final Handler handler = new Handler();
    final Handler handle = new Handler();
    protected void miThread(){
        Thread t = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                handler.post(proceso);
            }
        };
        t.start();
    }
    protected void miThread2(){
        Thread t2 = new Thread(){
            public void run(){
               try {
                        Thread.sleep(1000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
                handle.post(proceso2);
           }
        };
        t2.start();
    }
    final Runnable proceso2 = new Runnable() {
        @Override
        public void run() {
            velocidad.setText(""+150+"Km/h");
            temperatura.setText(""+60+"Âº");
        }
    };
    final Runnable proceso = new Runnable() {
        @Override
        public void run() {
            insertarRPM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rpmActualtext = textoRpm.getText().toString();
                    numerito = Integer.parseInt(rpmActualtext);

                    if(numerito > 0 && numerito< 4000){
                        layout1.setBackgroundColor(Color.GREEN);
                    }
                    if (numerito>4000 && numerito < 7500) {

                        layout1.setBackgroundColor(Color.YELLOW);
                        //rpmActual.setText(textoRpm.getText().toString());

                    }if(numerito > 7500) {
                        //layout1 = (RelativeLayout) findViewById(R.id.layout);
                        layout1.setBackgroundColor(Color.RED);

                    }
                }
            });
        }
    };


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void generarDatos(){
    	
		
		 
		SQLiteDatabase baseDatos=datosBd.getWritableDatabase();
		baseDatos.execSQL("DELETE FROM "+ "tablaDatos"+";");//borramos los datos de la base anterior
		if(baseDatos!=null)
		{
			for(int i=1;i<=5000;i++){
				int id=i;
				String nombre="Sensor 1= "+i;
				//baseDatos.execSQL("INSERT INTO tablaDatos(id,nombre)"+  "VALUES (" + id + ", '" + nombre +"')" );
				 
			}
			 baseDatos.close();
		}
		 baseDatos=datosBd.getWritableDatabase();
		
		//String[] campos = new String[] {"id", "nombre"};
		//String[] args = new String[] {"Usuario 4"}; seleccionar un registro
		//Cursor c = baseDatos.query("tablaDatos", campos,"nombre=?",args, null, null, null);
		String[] campos = new String[] {"id", "nombre"};
		//                                              where arg  groupby habving order by    
		Cursor c = baseDatos.query("tablaDatos", campos,null,null, null, null, null);//seleccionando todos los registros
		//resultado.setText("");
		//Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
		     //Recorremos el cursor hasta que no haya más registros
		     do {
		          String id= c.getString(0);
		          String nombre = c.getString(1);
		          //resultado.append("id:"+" " + id + " - " + nombre + "\n");
		     } while(c.moveToNext());
		}
		baseDatos.close();
    }
    
    public static void insertaDatos(int id,String nombre){
    	SQLiteDatabase baseDatos=datosBd.getWritableDatabase();
    	if(baseDatos!=null)
		{
    	
    	baseDatos.execSQL("INSERT INTO tablaDatos(id,nombre)"+  "VALUES (" + id + ", '" + nombre +"')" );
   
		}
    	 baseDatos.close();
    }
    //********************************************************************************************
    //     									USB
    //********************************************************************************************
    @Override
	protected void onResume() {
		super.onResume();
		
		if(myFileInputStream == null || myFileOutputStream == null){
			
			UsbAccessory[] usbAccessoryList = myUsbManager.getAccessoryList();
			UsbAccessory usbAccessory = null;
			if(usbAccessoryList != null){
				usbAccessory = usbAccessoryList[0];
				OpenUsbAccessory(usbAccessory);
			}
		}
	}
    private void OpenUsbAccessory(UsbAccessory acc){
		myParcelFileDescriptor = myUsbManager.openAccessory(acc);
		if(myParcelFileDescriptor != null){
			
			myUsbAccessory = acc;
			FileDescriptor fileDescriptor = myParcelFileDescriptor.getFileDescriptor();
			myFileInputStream = new FileInputStream(fileDescriptor);
			myFileOutputStream = new FileOutputStream(fileDescriptor);
			
			Thread thread = new Thread(myRunnable);
			thread.start();
		}
	}
	
	Runnable myRunnable = new Runnable(){

		@Override
		public void run() {
			int numberOfByteRead = 0;
			byte[] buffer = new byte[255];
			
			while(numberOfByteRead >= 0){
				
				try {
					numberOfByteRead = myFileInputStream.read(buffer, 0, buffer.length);
					final StringBuilder stringBuilder = new StringBuilder();
					for(int i=0; i<numberOfByteRead; i++){
						stringBuilder.append((char)buffer[i]);
					}
					
					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							textRxUSB.setText(stringBuilder.toString());
						}});
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}
		}
		
	};
	
	
	
	private void closeUsbAccessory(){
		
		if(myParcelFileDescriptor != null){
			try {
				myParcelFileDescriptor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		myParcelFileDescriptor = null;
		myUsbAccessory = null;
	}

	@Override
	protected void onPause() {
		super.onPause();
		closeUsbAccessory();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(myUsbReceiver);
	}
	
	private BroadcastReceiver myUsbReceiver = new BroadcastReceiver(){
		
		@Override
		public void onReceive(Context context, Intent intent) {

			String action = intent.getAction();
			if(action.equals(UsbManager.ACTION_USB_ACCESSORY_DETACHED)){
				
				Toast.makeText(MainActivity.this, "onReceive: ACTION_USB_ACCESSORY_DETACHED", Toast.LENGTH_LONG).show();
				
				UsbAccessory usbAccessory = UsbManager.getAccessory(intent);
				
				if(usbAccessory!=null && usbAccessory.equals(myUsbAccessory)){
					closeUsbAccessory();
				}
			}
		}
	};
    
    

     


}
