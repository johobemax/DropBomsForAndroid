package bemax.dropbomsforandroid;

import java.io.Serializable;
import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class SoundEffect  implements OnLoadCompleteListener,Serializable{
	private Context context;
	private SoundPool soundPool;
	private HashMap<Integer, Integer> idMap;

	public SoundEffect(Context con, int max){
		context = con;
		soundPool = new SoundPool(max, AudioManager.STREAM_MUSIC, 1);
		soundPool.setOnLoadCompleteListener(this);
		idMap = new HashMap<Integer, Integer>();
	}

	public void addSound(int res){
		int id = soundPool.load(context,res,1);
		if(id != 0){
			idMap.put(res, id);
		}
	}

	public SoundPool getSoundPool(){
		return soundPool;
	}

	public void play(int res){
		soundPool.play(idMap.get(res), 0.3f, 0.3f, 0, 0, 1);
	}

	public void release(){
		soundPool.release();
	}

	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		// TODO 自動生成されたメソッド・スタブ
		if(status == 0){

		}
	}
}
