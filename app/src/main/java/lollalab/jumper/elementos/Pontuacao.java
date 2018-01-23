package lollalab.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

import lollalab.jumper.engine.Som;
import lollalab.jumper.graficos.Cores;

/**
 * Created by Parafuso Solto on 05/10/2017.
 */

public class Pontuacao {

    private Som som;
    private int pontos = 0;
    private static final Paint BRANCO = Cores.getCorDaPontuacao();

    public Pontuacao(Som som){
        this.som = som;
    }

    public void desenhaNo(Canvas canvas) {
        canvas.drawText(String.valueOf(pontos), 100, 100, BRANCO);
    }

    public void aumenta() {
        som.toca(Som.PONTUACAO);
        pontos++;
    }
}
