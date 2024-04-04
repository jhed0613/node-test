package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
// Entity 는 데이터베이스의 테이블을 의미해줌 ex) Board
@Data
// Board.getTitle 형식으로 매개변수로 사용할 수 있음.
public class Board {
    @Id // primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

}
