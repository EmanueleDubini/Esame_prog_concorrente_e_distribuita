import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Definizione dei tipi di un editoriale (politica, attualita, scienza, sport)
 */
public enum EditorialeTipo {
    politica, attualita, scienza, sport;

    /**
     * utilizzato per la generazione casuale di un tipo di editoriale "getEditorialeTipoCasuale()"
     */
    private static final List<EditorialeTipo> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

    /**
     * utilizzato per la generazione casuale di un tipo di editoriale "getEditorialeTipoCasuale()"
     */
    private static final int SIZE = VALUES.size();

    /**
     * utilizzato per la generazione casuale di un tipo di editoriale "getEditorialeTipoCasuale()"
     */
    private static final Random RANDOM = new Random();

    /**
     * Generazione casuale del tipo di un editoriale
     */
    public static EditorialeTipo getEditorialeTipoCasuale()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
