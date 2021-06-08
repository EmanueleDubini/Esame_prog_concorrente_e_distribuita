package server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EditorialeTipo {
    politica, attualita, scienza, sport;

    private static final List<EditorialeTipo> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EditorialeTipo getEditorialeTipoCasuale()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
