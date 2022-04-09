package com.example.password.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.password.Componment.Base64Utils;
import com.example.password.Componment.URLDownLoadUtils;
import com.example.password.Componment.ZipUtils;
import com.example.password.Pojo.FileInformation;
import com.example.password.Service.FileRecognitionRunnable;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {

    final static String TempPath= "D:/SpringWorkSpace/PassWord/files/";

    @PostMapping("/test")
    public String testFunction(HttpServletRequest request
                               ){
        String param= null;
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
            param= jsonObject.toJSONString();
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    @PostMapping("/test/chooseAl")
    public String newTest(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject.toJSONString() + "\n" +
                jsonObject.getString("filePath") + "  " +
                jsonObject.getString("name") + "  " +
                jsonObject.getString("modelPath") + " ");
        JSONObject outJson = new JSONObject();
        outJson.put("label","success");
        return outJson.toJSONString();
    }

    @GetMapping("/welcome")
    public String welcome() throws JsonProcessingException {
        String url = "http://localhost:8079/newtest";
        RestTemplate restTemplate = new RestTemplate();
        /*
        User user = new User(1,"65151");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        */

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        JSONObject param = new JSONObject();
        param.put("id", 1);
        param.put("password","458464");

        HttpEntity<String> formEntity = new HttpEntity<String>(param.toJSONString(), headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,formEntity,String.class);
        return responseEntity.getBody();
    }

    @PostMapping("/file/zip")
    public static void extZip(@RequestParam(value = "zipfile",required = false) MultipartFile multipartFile) throws Exception {
        ZipUtils.unzip(multipartFile,"D:\\SpringWorkSpace\\PassWord\\files\\");
    }

    @GetMapping("/test/getFile")
    public static String getFileByUrl(@RequestParam("url") String url) throws IOException {
        File urlFile = URLDownLoadUtils.getNetUrl(url);
        InputStream inputStream = new FileInputStream(urlFile);
        File file = new File(TempPath+"temp");
        OutputStream outputStream = new FileOutputStream(file);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
            outputStream.write(buffer,0,len);
        }
        return TempPath+"temp";
    }


    //关于json的解析
    @PostMapping("/test/yt")
    public String getJson(@RequestBody JSONObject jsonObject){
        String str =  jsonObject.getString("name");
        JSONArray arr= jsonObject.getJSONArray("data");
        Object[] objs= arr.stream().toArray();
        List<String> list = new ArrayList<String>();
        for (Object obj : objs) {
               list.add(obj.toString());
               System.out.println(obj.toString());
        }
        System.out.println(list.size());
        return str+list.toString();
    }

    @PostMapping("/test/base64")
    public String getBase64File(@RequestBody JSONObject jsonObject){
        String fileName = jsonObject.getString("name");
        String base64String = jsonObject.getString("data");
        String outFilePath = Base64Utils.decodeBase64String("txt",base64String,TempPath);
        return fileName + outFilePath;
    }

    @Autowired
    FileRecognitionRunnable runnable = new FileRecognitionRunnable();

    @PostMapping("/test/file/pass")
    public String sendFileInformation(  @RequestParam("url") String url,
                                        @RequestParam("modelPath") String modelPath,
                                        @RequestBody FileInformation fileInformation){
        System.out.println(fileInformation);
        runnable.giveValue(url,modelPath,fileInformation);
        Thread thread = new Thread(runnable);
        thread.start();
        return "success";
    }



}
