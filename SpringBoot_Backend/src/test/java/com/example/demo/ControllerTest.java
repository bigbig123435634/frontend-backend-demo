package com.example.demo;

import com.example.demo.Service.ArticalService;
import com.example.demo.controller.ArticalController;


import com.example.demo.model.ArticalVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticalController.class)
public class ControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    @MockitoBean
    private ArticalService articalService;



    @Test
    public void getArticalTest() throws Exception {
        // 准备测试数据
        ArticalVO mockArtical = new ArticalVO();
        mockArtical.setId(1L);
        mockArtical.setTittle("Book");
        mockArtical.setAuthor("Petter");
        mockArtical.setContent("content x 1000");

        // 模拟 Service
        when(articalService.getArtical(1L)).thenReturn(mockArtical);

        // 执行测试
        mockMvc.perform(get("/artical/1"))
                //.andExpect(status().isOk())                      // 验证 Status = 200
                //.andExpect(status().isCreated())                 // 验证 Status = 201
                //.andExpect(status().isBadRequest())             // 验证 Status = 400
                //.andExpect(status().isNotFound())               // 验证 Status = 404
                //.andExpect(status().is5xxServerError())         // 验证 Status = 500-599
                .andExpect(status().isOk())


                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.isok").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.tittle").value("Book"))

                .andExpect(jsonPath("$.data.author").isString())
                .andExpect(jsonPath("$.data.author").value("Petter"))

                .andExpect(jsonPath("$.data.content").value("content x 1000"))
                .andExpect(jsonPath("$.data.reader").isEmpty());


        verify(articalService, times(1)).getArtical(1L);
    }

    @Test
    public void getAllTest() throws Exception {

        List<ArticalVO> mockArticles = List.of(
                ArticalVO.builder().id(1L).tittle("Book").content("内容1").author("Petter").build(),
                ArticalVO.builder().id(2L).tittle("Look").content("内容2").author("Petter").build(),
                ArticalVO.builder().id(3L).tittle("Took").content("内容3").author("Petter").build(),
                ArticalVO.builder().id(4L).tittle("Book").content("内容4").author("May").build()
        );

        when(articalService.getAll()).thenReturn(mockArticles);

        mockMvc.perform(get("/artical"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.isok").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(4))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[1].tittle").value("Look"))
                .andExpect(jsonPath("$.data[3].author").value("May"));

    }

    @Test
    public void saveArtical() throws Exception{

        ArticalVO mockArtical = new ArticalVO();
        mockArtical.setId(1L);
        mockArtical.setTittle("Book");
        mockArtical.setAuthor("Petter");
        mockArtical.setContent("content x 1000");

        String json = objectMapper.writeValueAsString(mockArtical);

        doNothing().when(articalService).saveArtical(any(ArticalVO.class));

        mockMvc.perform(post("/artical")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isok").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("save a article"))
                .andExpect(jsonPath("$.data").isEmpty());

        verify(articalService, times(1)).saveArtical(any(ArticalVO.class));
    }

    @Test
    public void updatesaveArticalTest ()throws Exception{

        ArticalVO a1= ArticalVO.builder().id(1L).tittle("Book").content("内容1").author("Petter").build();
        ArticalVO a2 = ArticalVO.builder().id(1L).tittle("Hook").content("内容4").author("May").build();

        String json = objectMapper.writeValueAsString(a2);

        when(articalService.getArtical(1L)).thenReturn(a1);
        doNothing().when(articalService).updataArtical(any(ArticalVO.class));

        mockMvc.perform(put("/artical")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isok").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isEmpty());


        verify(articalService, times(1)).getArtical(1L);
        verify(articalService, times(1)).updataArtical(any(ArticalVO.class));
    }
    @Test
    public void deleteArticalTest() throws Exception{

        ArticalVO a1= ArticalVO.builder().id(1L).tittle("Book").content("内容1").author("Petter").build();

        when(articalService.getArtical(1L)).thenReturn(a1);
        doNothing().when(articalService).deleteArtical(1L);


        mockMvc.perform(delete("/artical/1")
                        .contentType(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isok").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isEmpty());


        verify(articalService, times(1)).getArtical(1L);
        verify(articalService, times(1)).deleteArtical(1L);
    }

}
