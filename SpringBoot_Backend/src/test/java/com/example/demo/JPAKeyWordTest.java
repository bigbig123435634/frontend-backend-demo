package com.example.demo;

import com.example.demo.dao.ArticalRepository;
import com.example.demo.model.Artical;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JPAKeyWordTest {

    @Resource
    private ArticalRepository articalRepository;

    @BeforeEach
    void setUp() {
        // clear all data
        articalRepository.deleteAll();

        // prepare data
        List<Artical> articles = List.of(
                Artical.builder().tittle("Book").content("内容1").author("Petter").build(),
                Artical.builder().tittle("Look").content("内容2").author("Petter").build(),
                Artical.builder().tittle("Took").content("内容3").author("Petter").build(),
                Artical.builder().tittle("Book").content("内容4").author("May").build()
        );

        //save data
        articalRepository.saveAll(articles);
    }

    @Test
    public  void jpsTestFindByAuthor(){
        List<Artical> articles = articalRepository.findByAuthor("Petter");
        //result not null
        assertNotNull(articles);
        //size = 3
        assertEquals(3, articles.size());
        assertEquals("Petter", articles.get(0).getAuthor());
        assertEquals("Petter", articles.get(1).getAuthor());
        assertEquals("Petter", articles.get(2).getAuthor());

        // 打印结果
        System.out.println("查询结果:");

        articles.forEach(System.out::println);

    }


    @Test
    public  void jpsTestFindByAuthorOrIdOrTittle(){

        List<Artical> articles = articalRepository.findByAuthorOrIdOrTittle("May",null,"Book",null);
        assertEquals("Book", articles.get(0).getTittle());
        assertEquals("Petter", articles.get(0).getAuthor());
        assertEquals("Book", articles.get(1).getTittle());
        assertEquals("May", articles.get(1).getAuthor());
        System.out.println("查询结果:");
        System.out.println(articles);
    }


    @Test
    public  void jpsTestFfindByIdLessThanEqual(){
        List<Artical> articles = articalRepository.findByIdLessThanEqual(50L);

        assertTrue(50>=articles.get(0).getId());
        assertTrue(50>= articles.get(1).getId());
        assertTrue(50 >=articles.get(2).getId());

        System.out.println("查询结果:");

        articles.forEach(System.out::println);

    }

    @Test
    public  void jpsTestFindByAuthorAndIdAndTittle(){
        List<Artical> articles = articalRepository.findByAuthorAndTittle("May","Book",null);

        assertEquals("Book", articles.get(0).getTittle());
        assertEquals("May", articles.get(0).getAuthor());
        System.out.println("查询结果:");
        System.out.println(articles);
    }

}
