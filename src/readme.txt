
Compilazione e preparazione, supponendo che i sorgenti siano in /Desktop/esame

(per Windows \Desktop\esame)



Compilazione:
-------------

- Creare una directory vuota, per esempio sul Desktop, e chiamarla "esame"

- Copiare tutti i files con estensione .java dalla dir /src del file compresso nella cartella "esame"

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