package Model;

import java.util.Arrays;

public enum EstadoVehiculo {
    DISPONIBLE(1),
    NO_DISPONIBLE(2);
    private final int value;

    EstadoVehiculo(int value) {
        this.value = value;
    }

    public static EstadoVehiculo  getTypeByValue(int value){
        return Arrays.stream(EstadoVehiculo.values())
                .filter(e->e.value==value)
                .findFirst()
                .orElseThrow();
    }
}
