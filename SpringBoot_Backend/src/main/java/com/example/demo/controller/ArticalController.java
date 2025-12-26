package com.example.demo.controller;

import com.example.demo.model.AResponse;
import com.example.demo.model.ArticalVO;
import com.example.demo.Service.ArticalService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//@Controller
//@RequestMapping("/articals/") 再加 @ResponseBody 在每一個function 前
public class ArticalController {

    @Resource
    ArticalService articalService;

    @GetMapping("/artical/{id}")
    public  AResponse getArical(@PathVariable("id") Long id){

        ArticalVO articalVO = articalService.getArtical(id);

        if (articalVO == null){
            String message = "artical No."+ String.valueOf(id) +" cannot be found";
            return AResponse.error(null, message);

        }else {

            log.info("get a article:"+ articalVO);
            return AResponse.success(articalVO);
        }
    }

    @GetMapping("/artical")
    public  AResponse getAll(){

        List<ArticalVO> VOarticals =articalService.getAll();

        log.info("get a article:"+ VOarticals);

        return AResponse.success(VOarticals);

    }

    @PostMapping("/artical")
    public AResponse saveArical(@RequestBody ArticalVO articalVO){
                                       //@RequestParm String author 就是用表單提交

         articalService.saveArtical(articalVO);

        log.info("save a article:"+ articalVO);
        String message = "save a article";

    return AResponse.success(null, message);

    }
    @PutMapping("/artical")
    public AResponse updateArical(@RequestBody ArticalVO articalVO){

        if (articalService.getArtical(articalVO.getId()) == null){
            String message = "Artical No."+ String.valueOf(articalVO.getId()) +" cannot be found";
            return AResponse.error(null, message);
        }else {

            articalService.updataArtical(articalVO);

            log.info("update a article:"+ articalVO);

            return AResponse.success();
        }
    }

    @DeleteMapping("/artical/{id}")
    public AResponse deleteArical(@PathVariable("id") Long id){

        ArticalVO articalVO = articalService.getArtical(id);

        if (articalVO == null){
            String message = "Artical No."+ String.valueOf(id) +"does not exist";
            return AResponse.error(null, message);
        }else {

            articalService.deleteArtical(id);

            log.info("update a article:"+ articalVO);

            return AResponse.success();
        }



    }
}
