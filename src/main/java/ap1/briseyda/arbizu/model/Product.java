package ap1.briseyda.arbizu.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "product")
public class Product {

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "nombre")
    private String nombre;
    @Column(value = "descripcion")
    private String descripcion;
    @Column(value = "precio")
    private double precio;
    @Column(value = "stock")
    private int stock;
    @Column(value = "categoria")
    private String categoria;
    @Column(value = "marca")
    private String marca;
    @Column(value = "estado")
    private Boolean estado;

}
