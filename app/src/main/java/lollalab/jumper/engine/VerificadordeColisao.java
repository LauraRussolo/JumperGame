package lollalab.jumper.engine;

import lollalab.jumper.elementos.Canos;
import lollalab.jumper.elementos.Passaro;

/**
 * Created by Parafuso Solto on 05/10/2017.
 */

public class VerificadordeColisao {

    private final Passaro passaro;
    private final Canos canos;

    public VerificadordeColisao(Passaro passaro, Canos canos){
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao(){
        return canos.temColisaoCom(passaro);
    }
}
