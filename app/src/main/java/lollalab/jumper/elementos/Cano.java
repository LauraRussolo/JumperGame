package lollalab.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import lollalab.jumper.R;
import lollalab.jumper.graficos.Cores;
import lollalab.jumper.graficos.Tela;

/**
 * Created by Parafuso Solto on 02/10/2017.
 */

public class Cano {

    private final Tela tela;

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private Bitmap imgCano;
    private Paint VERDE = Cores.getCorDoCano();
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;

    public Cano (Tela tela, int posicao, Context context){
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = TAMANHO_DO_CANO + valorAleatorio();

        imgCano = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 150);
    }

    public void desenhaNo(Canvas canvas) {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorNo(canvas);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas) {
        Bitmap canoSuperior = Bitmap.createScaledBitmap(imgCano, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
        canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }

    public void desenhaCanoInferiorNo(Canvas canvas){
        Bitmap canoInferior = Bitmap.createScaledBitmap(imgCano, LARGURA_DO_CANO, alturaDoCanoInferior, false);
        canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }

    public void move() {
        posicao -=5;
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoVerticalCom(Passaro passaro) {
        return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior
                ||
                passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro){
        return this.posicao < passaro.X + passaro.RAIO  ;
    }
}
