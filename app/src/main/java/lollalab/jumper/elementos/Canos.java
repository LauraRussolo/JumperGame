package lollalab.jumper.elementos;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import lollalab.jumper.engine.Som;
import lollalab.jumper.graficos.Tela;

/**
 * Created by Parafuso Solto on 02/10/2017.
 */

public class Canos {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private Som som;
    private Context context;
    private Pontuacao pontuacao;
    private Tela tela;
    private List<Cano> canos = new ArrayList<Cano>();

    public Canos(Tela tela, Pontuacao pontuacao, Context context, Som som){
        this.som = som;
        this.context = context;
        this.tela = tela;
        this.pontuacao = pontuacao;
        int posicaoInicial = 800;

        for(int i=0; i< QUANTIDADE_DE_CANOS; i++){
            Cano cano = new Cano(tela, posicaoInicial, context);
            canos.add(cano);
            posicaoInicial += DISTANCIA_ENTRE_CANOS; //incrementa a distância para criar um novo cano
        }
    }

    public void desenhaNo(Canvas canvas){
        for(Cano cano : canos){
            cano.desenhaNo(canvas);
        }

    }

    public void move() {
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()){
            Cano cano = iterator.next();
            cano.move();
            if(cano.saiuDaTela()){
                pontuacao.aumenta();
                iterator.remove();
                Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
                iterator.add(outroCano);
            }
        }
    }

    private int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos){
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;


        //ou apenas:
        //canos.size()
        //pega posicao do ultimo cano no array e retorna
        //não sei pq aquele for, talvez ele use pra algo depois. Aí eu apago esse comentário, mas por hora serviria
    }

    public boolean temColisaoCom(Passaro passaro) {
        for(Cano cano: canos){
            if(cano.temColisaoVerticalCom(passaro) && cano.temColisaoHorizontalCom(passaro)){
                som.toca(Som.COLISAO);
                return true;
            }
        }
        return false;
    }
}
