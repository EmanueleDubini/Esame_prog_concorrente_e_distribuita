package server;

import client.FruitoreNotizie;

public class InfoAbbonato {
    private final FruitoreNotizie fruitoreRemoto;
    private boolean politica = false;
    private boolean attualita = false;
    private boolean scienza = false;
    private boolean sport = false;

    public InfoAbbonato(FruitoreNotizie f) {
        this.fruitoreRemoto = f;
    }


}
