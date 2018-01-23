package lollalab.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import lollalab.jumper.R;
import lollalab.jumper.engine.Som;
import lollalab.jumper.graficos.Cores;
import lollalab.jumper.graficos.Tela;

/**
 * Created by Parafuso Solto on 26/09/2017.
 */

public class Passaro {

    public static final int X = 100;
    public static final int RAIO = 50;
    private static final Paint VERMELHO = Cores.getCorDoPassaro();
    private Bitmap passaro;
    private Tela tela;
    private int altura = 200;
    private Som som;


    public Passaro(Tela tela, Context context, Som som){
        this.tela = tela;
        this.altura = 100;
        this.som = som;

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO * 2, RAIO * 2, false);
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawBitmap(passaro, X-RAIO, altura-RAIO, null);
    }

    public void cai() {
        boolean chegouNochao = altura+RAIO > tela.getAltura();
        if(!chegouNochao){
            altura +=5; //tira 5 px da altura do passaro
        }
    }

    public void pula(){
        boolean chegouNoTopo = altura < RAIO;
        if(!chegouNoTopo){
            som.toca(Som.PULO);
            altura -= 100; //adiciona 15 px a altura do passaro
        }
    }

    public int getAltura() {
        return this.altura;
    }
}
