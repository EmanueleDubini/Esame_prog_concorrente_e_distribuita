
Una volta estratto il file CasalnovoDubini.zip verra mostrato:

- una cartella "Esame" che contiene i file .java suddivisi in due cartelle, "client" e "server", le quali contengono tutto il necessario
  per eseguire i due programmi singolarmente e in ambiente distribuito

- una cartella "src" contenente i file .java dell'intero progetto (comprendente client e server), utilizzabili per aprire il progetto tramite un IDE
Compilazione e preparazione, supponendo che i sorgenti siano in /Desktop/esame




Esecuzione da IDE:
-------------
Passaggi:
1) Creare un nuovo progetto utilizzando l'ide di programmazione preferita
2) Aprire la cartella "src" estratta dal file CasalnovoDubini.zip e copiare tutti i file .java contenuti al suo interno
3) Incollare i file copiati nella cartella "src" del progetto all'interno della propria IDE

Ora il progetto è pronto per essere utilizzato



Esecuzione da Terminale:
-------------
(Note Preliminari: a scopo semplificativo si considerera il percorso delle due cartelle "client" e "server", contenute nella cartella "esame", equivalente a:  ~/Desktop/esame)

La fase di compilazione ed esecuzione equivale sia per l'applicazione CLient che per quella Server.


Compilazione:
-------------

- Creare una directory vuota, per esempio sul Desktop, e chiamarla "esame"

- Copiare tutti i files con estensione .java, del "client" o del "server", dalla dir /src del file compresso nella cartella "esame"

- Aprire una finestra del terminale e raggiungere tramite il percorso la posizione della cartella "esame"

- Compilare tutti i files .java col seguente comando: javac *.java



Esecuzione:
-----------

Aprire diversi terminali che puntano alla directory "esame" (o il nome che avete scelto) ed eseguire i programmi Java:

terminale 1 (SERVER):
cd ~/Desktop/esame
java ServerMain

terminale 2 (Client1):
cd ~/Desktop/esame
java FruitoreNotizieImpl

terminale 3 (Client2):
cd ~/Desktop/esame
java FruitoreNotizieImpl

terminale 4 (Client-n):
cd ~/Desktop/esame
java FruitoreNotizieImpl

(NOTA)
Il programma puo essere eseguito specificando l'indirizzo ip del server:

java FruitoreNotizieImpl <ip_del_server>

per ogni terminale in cui si vuole eseguire il client in un ambiente distribuito.
L'indirizzo del server viene stampato nel suo relativo terminale nel momento in cui viene mandato in esecuzione, offrendo la possibilita di copiarlo

Nel caso in cui non verra specificato alcun indirizzo ip al momento dell'esecuzione verra considerato in automatico dall'applicazione quello del LOCAL-HOST