package bemax.dropbomsforandroid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverScreen implements OnTouchListener {
	private Handler handler;
	private int score;
	private int get;

	public GameOverScreen(int[] data, Activity act, Handler h){
		handler = h;
		score = data[0];
		get = data[1];

		act.setContentView(R.layout.gameover);

	    if(score >= 0){
		    int highscore = 0;

		    try {
				FileInputStream is = act.openFileInput("dropbomshighscore.dat");
				DataInputStream dis = new DataInputStream(is);
				highscore = dis.readInt();
				is.close();
			} catch (Exception e){
				e.printStackTrace();
				try{
					FileOutputStream os = act.openFileOutput("dropbomshighscore.dat", Context.MODE_PRIVATE);
					DataOutputStream dos = new DataOutputStream(os);
					dos.writeInt(0);
					os.close();
				}catch(IOException ee){
					ee.printStackTrace();
				}
			}

	    	TextView tv = (TextView)act.findViewById(R.id.score_text);
	    	tv.setText("SCORE: "+score);

	    	TextView htv = (TextView)act.findViewById(R.id.high_score_text);
	    	if(score > highscore){
	    		htv.setText("New Record !\nOld Recode " + highscore);
	    		try{
	    			FileOutputStream os = act.openFileOutput("dropbomshighscore.dat",Context.MODE_PRIVATE);
	    			DataOutputStream dos = new DataOutputStream(os);
	    			dos.writeInt(score);
	    			os.close();
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}else{
	    		htv.setText("HIGH SCORE: "+highscore);
	    	}

	    	TextView gtv = (TextView)act.findViewById(R.id.get_text);
	    	gtv.setText("Get:" + get + "%");
		}

	    ImageView image = (ImageView)act.findViewById(R.id.game_over_image);
	    image.setOnTouchListener(this);
	}

	public boolean onTouch(View v, MotionEvent me) {
		handler.sendEmptyMessage(0);
		return false;
	}
}
