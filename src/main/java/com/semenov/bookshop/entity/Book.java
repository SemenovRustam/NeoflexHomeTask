package com.semenov.bookshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer coast;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
    joinColumns = @JoinColumn(name = "book_id")
    , inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> author;

    public Book(Long id, String name, Integer coast) {
        this.id = id;
        this.name = name;
        this.coast = coast;
    }
}
