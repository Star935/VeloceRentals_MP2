package Model;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaccion {
    private Integer id;
    private Reserva reserva;
    private Integer monto;
    private String metodo_pago;
    private Date fecha;
    private EstadoTransaccion estado;
}
