package com.example.demo.Service;


import com.example.demo.dao.ArticalRepository;
import com.example.demo.model.Artical;
import com.example.demo.model.ArticalVO;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
public class ArticalJPAServiceImpl implements ArticalService{

    @Resource
    private  ArticalRepository articalRepository;
    @Resource
    private  ModelMapper modelMapper;



    public void  saveArtical(ArticalVO articalVO){
        Artical artical = modelMapper.map(articalVO, Artical.class);
        articalRepository.save(artical);
    }

    public void deleteArtical(Long id){
        articalRepository.deleteById(id);

    }

    public void updataArtical (ArticalVO articalVO){
        Artical artical = modelMapper.map(articalVO, Artical.class);
        articalRepository.save(artical);
    }

    public  ArticalVO getArtical (Long id){
        Optional<Artical> artical = articalRepository.findById(id);
        return artical.map(value -> modelMapper.map(value, ArticalVO.class))
                .orElse(null);
    }

    public List<ArticalVO> getAll(){
        List<Artical> articalList= articalRepository.findAll();
        return articalList.stream()
                .map(artical -> modelMapper.map(artical, ArticalVO.class))
                .collect(Collectors.toList());
    }
}
