package com.example.password.Service;

import com.example.password.Pojo.IndexImg;
import com.example.password.mapper.InstructionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    @Autowired
    InstructionMapper instructionMapper;

    public List<IndexImg> getIndexImg(){
        int i = 1;
        List<IndexImg> outImg = new ArrayList<>();
        List<IndexImg> inImg;
        inImg = instructionMapper.getIndexImg();
        for (IndexImg img:inImg) {
            outImg.add(new IndexImg(i, img.getImgPath()));
            i++;
        }
        return outImg;
    }
}
