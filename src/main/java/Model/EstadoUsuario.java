package Model;

import java.util.Arrays;

public enum EstadoUsuario {
    ACTIVO(1),
    INACTIVO(2);
    private final int value;

    EstadoUsuario(int value) {
        this.value = value;
    }

    public static EstadoUsuario  getTypeByValue(int value){
        return Arrays.stream(EstadoUsuario.values())
                .filter(e->e.value==value)
                .findFirst()
                .orElseThrow();
    }
}
