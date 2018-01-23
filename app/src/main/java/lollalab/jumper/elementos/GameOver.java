package lollalab.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import lollalab.jumper.graficos.Cores;
import lollalab.jumper.graficos.Tela;

/**
 * Created by Parafuso Solto on 06/10/2017.
 */

public class GameOver {
    private Tela tela;
    private static final Paint VERMELHO = Cores.getCorDoGameOver();

    public GameOver(Tela tela){
        this.tela = tela;
    }

    public void desenhaNo(Canvas canvas){
        String texto = "Game Over";
        int centroHorizontal = centralizaTexto(texto);
        canvas.drawText(texto, centroHorizontal , tela.getAltura()/2, VERMELHO);
    }

    private int centralizaTexto(String texto) {
        Rect limitesDoTexto = new Rect();
        VERMELHO.getTextBounds(texto,0, texto.length(), limitesDoTexto); //coloca na variavel limitesDoTexto o retangulo mínimo aonde cabe o texto dado nas especificações do Pain vERMELHO
        int centroTexto = tela.getLargura()/2 - limitesDoTexto.centerX();
        return centroTexto;
    }
}
