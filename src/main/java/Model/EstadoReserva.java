package Model;

import java.util.Arrays;

public enum EstadoReserva {
    PENDIENTE(1),
    CONFIRMADA(2),
    CANCELADA(3);

    private final int value;

    EstadoReserva(int value) {
        this.value = value;
    }
    public static EstadoReserva  getTypeByValue(int value){
        return Arrays.stream(EstadoReserva.values())
                .filter(e->e.value==value)
                .findFirst()
                .orElseThrow();
    }
}
