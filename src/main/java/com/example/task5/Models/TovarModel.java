package com.example.task5.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tovar")
public class TovarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tovar_id", updatable = false, nullable = false)
    private Integer id;
    private String name;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private ClientModel client_id;
}
