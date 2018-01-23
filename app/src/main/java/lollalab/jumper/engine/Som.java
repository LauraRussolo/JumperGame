package lollalab.jumper.engine;

import android.content.Context;
import android.media.SoundPool;

import lollalab.jumper.R;

/**
 * Created by Parafuso Solto on 10/10/2017.
 */

public class Som {

    public static int PULO;
    public static int PONTUACAO;
    public static int COLISAO;
    private SoundPool soundPool;

    public Som(Context context){
        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .build();

        PULO = soundPool.load(context, R.raw.pulo, 1);
        PONTUACAO = soundPool.load(context, R.raw.pontos, 1);
        COLISAO = soundPool.load(context, R.raw.colisao, 1);
    }

    public void toca(int som){
        soundPool.play(som,1,1,1,0,1);
    }
}
