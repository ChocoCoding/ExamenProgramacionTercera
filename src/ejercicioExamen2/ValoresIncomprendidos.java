package ejercicioExamen2;

import java.util.InputMismatchException;

public class ValoresIncomprendidos extends IllegalArgumentException {
    public ValoresIncomprendidos(String s) {
        super(s);
    }
}
