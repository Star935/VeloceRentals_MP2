package Model;

import java.util.Arrays;

public enum EstadoTransaccion {
    PENDIENTE(1),
    FINALIZADA(2),
    RECHAZADA(3);
    private final int value;

    EstadoTransaccion(int value) {
        this.value = value;
    }

    public static EstadoTransaccion  getTypeByValue(int value){
        return Arrays.stream(EstadoTransaccion.values())
                .filter(e->e.value==value)
                .findFirst()
                .orElseThrow();
    }
}