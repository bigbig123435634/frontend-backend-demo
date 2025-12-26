package com.example.demo.Service;

import com.example.demo.model.ArticalVO;
import java.util.List;

public interface ArticalService {
    void saveArtical(ArticalVO artical);

    void deleteArtical (Long id);

    void updataArtical (ArticalVO artical);

    ArticalVO getArtical (Long id);

    List<ArticalVO> getAll();
}
