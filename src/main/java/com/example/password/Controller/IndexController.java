package com.example.password.Controller;

import com.example.password.Pojo.IndexImg;
import com.example.password.Pojo.RetResult;
import com.example.password.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    IndexService indexService =new IndexService();
    @GetMapping("/instruction")
    public RetResult getInstruction(){
        RetResult<List<IndexImg>> result;
        List<IndexImg> imgs = indexService.getIndexImg();
        result = new RetResult<List<IndexImg>>(200,"success",imgs);
    return result;
    }
}
