package bemax.dropbomsforandroid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverActivity extends Activity implements OnClickListener{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // TODO Auto-generated method stub
	    setContentView(R.layout.gameover);

	    Intent intent = getIntent();
	    int score = intent.getIntExtra("score", -1);
	    int get = intent.getIntExtra("get", -1);

	    if(score >= 0){
		    int highscore = 0;

		    try {
				FileInputStream is = this.openFileInput("dropbomshighscore.dat");
				DataInputStream dis = new DataInputStream(is);
				highscore = dis.readInt();
				is.close();
			} catch (Exception e){
				e.printStackTrace();
				try{
					FileOutputStream os = this.openFileOutput("dropbomshighscore.dat", MODE_PRIVATE);
					DataOutputStream dos = new DataOutputStream(os);
					dos.writeInt(0);
					os.close();
				}catch(IOException ee){
					ee.printStackTrace();
				}
			}

	    	TextView tv = (TextView)findViewById(R.id.score_text);
	    	tv.setText("SCORE: "+score);

	    	TextView htv = (TextView)findViewById(R.id.high_score_text);
	    	if(score > highscore){
	    		htv.setText("New Record !\nOld Recode " + highscore);
	    		try{
	    			FileOutputStream os = this.openFileOutput("dropbomshighscore.dat", MODE_PRIVATE);
	    			DataOutputStream dos = new DataOutputStream(os);
	    			dos.writeInt(score);
	    			os.close();
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}else{
	    		htv.setText("HIGH SCORE: "+highscore);
	    	}

	    	TextView gtv = (TextView)findViewById(R.id.get_text);
	    	gtv.setText("Get:" + get + "%");
		}

	    ImageView image = (ImageView)findViewById(R.id.game_over_image);
	    image.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		this.finish();
	}



}
