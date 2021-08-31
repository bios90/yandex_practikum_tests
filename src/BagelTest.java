import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BagelTest {

    public static void main(String[] args) {
        Bagel b = getBagel();
        System.out.println(b.getValue());
    }

    public static Bagel getBagel() {
        try {
            Field b = Boolean.class.getDeclaredField("TRUE");
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(b, b.getModifiers() & ~Modifier.FINAL);
            b.setAccessible(true);
            b.set(null, Boolean.FALSE);
        } catch (Exception e) {
        }
        return new Bagel();
    }
}

class Bagel {
    public final int getValue() {
        return 3;
    }
}
