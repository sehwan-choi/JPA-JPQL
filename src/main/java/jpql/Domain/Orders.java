package jpql.Domain;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    private int orderAmouint;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
