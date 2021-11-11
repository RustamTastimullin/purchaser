package ru.domain.purchaser.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "in_number")
    private String inNumber;

    @Column(name = "out_number")
    private String outNumber;

    @Column(name = "link")
    private String link;

    @Column(name = "price")
    private String price;

    @Column(name = "topic")
    private String topic;

    @Column(name = "status")
    private String status;

    @Column(name = "stop_price")
    private String stopPrice;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Purchase() {
    }

    public Purchase(String inNumber,
                    String outNumber,
                    String link,
                    String price,
                    String topic,
                    String status,
                    String stopPrice,
                    String comment,
                    User user
                    ) {
        this.inNumber = inNumber;
        this.outNumber = outNumber;
        this.link = link;
        this.price = price;
        this.topic = topic;
        this.status = status;
        this.stopPrice = stopPrice;
        this.comment = comment;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

}