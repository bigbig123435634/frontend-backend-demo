package com.example.demo.dao;

import com.example.demo.model.Artical;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticalRepository extends JpaRepository<Artical,Long> {

    List<Artical> findByAuthor (String author);


    List<Artical> findByIdLessThanEqual(Long idIsLessThan);

    List<Artical> findByAuthorOrIdOrTittle(String author, Long id, String tittle, Limit limit);

    //And condition 冇咩用
    List<Artical> findByAuthorAndTittle(String author, String tittle, Limit limit);
}
