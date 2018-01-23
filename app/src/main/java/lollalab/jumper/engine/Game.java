package lollalab.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import lollalab.jumper.R;
import lollalab.jumper.elementos.Cano;
import lollalab.jumper.elementos.Canos;
import lollalab.jumper.elementos.GameOver;
import lollalab.jumper.elementos.Passaro;
import lollalab.jumper.elementos.Pontuacao;
import lollalab.jumper.graficos.Tela;

/**
 * Created by Parafuso Solto on 26/09/2017.
 */

public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private final Context context;
    private Tela tela;
    private boolean isrunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Canos canos;
    private Pontuacao pontuacao;
    private Som som;

    public Game(Context context) {
        super(context);
        this.context = context;
        this.tela = new Tela(context);
        this.som = new Som(context);
        setOnTouchListener(this);
        inicializaElementos();
    }

    private void inicializaElementos(){
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
        this.pontuacao = new Pontuacao(som);
        this.passaro = new Passaro(tela, context, som);
        this.canos = new Canos(tela, pontuacao, context, som);
    }

    @Override
    public void run() {
        //loop do jogo
        while(isrunning){
            if(!holder.getSurface().isValid()) continue; //se o canvas não estiver pronto, não executa o restante

            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);
            passaro.desenhaNo(canvas);
            canos.desenhaNo(canvas);
            pontuacao.desenhaNo(canvas);

            if(new VerificadordeColisao(passaro, canos).temColisao()){
                isrunning = false;
                new GameOver(tela).desenhaNo(canvas);
            }

            passaro.cai();
            canos.move();

            holder.unlockCanvasAndPost(canvas);
            
        }
    }

    public void inicia() {
        isrunning = true;
    }

    public void cancela(){
        isrunning = false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        passaro.pula();
        return false;
    }
}
