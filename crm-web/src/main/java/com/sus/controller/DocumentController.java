package com.sus.controller;

import com.sus.pojo.Document;
import com.sus.service.DocumentService;
import com.sus.util.ShiroUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by tgdsl on 2017/3/18.
 */
@Controller
@RequestMapping("/doc")
public class DocumentController {

    @Value("${imagePath}")
    private String savepath;
    @Autowired
    private DocumentService documentService;

    public String list(Model model, @RequestParam(required = false,defaultValue = "0")Integer fid){
        List<Document> documentList  = documentService.findDocumentByFid(fid);
        model.addAttribute("documentList",documentList);
        model.addAttribute("fid",fid);
        return  "document/list";
    }
    /**
     * 保存新的文件
     */
    public String saveDir(String name,Integer fid){
        documentService.saveDir(name,fid);
        return "redirect:/doc?fig="+fid;
    }


    public void save(InputStream inputStream,String originalFilename,String contentType,Long size,Integer fid){
        String extName = "";
        if(originalFilename.lastIndexOf(".")!=-1){
            extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString()+extName;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savepath, newFileName));
            IOUtils.copy(inputStream,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Document document = new Document();
        document.setName(originalFilename);
        document.setFid(fid);
        document.setContenttype(contentType);
        document.setCreatuser(ShiroUtils.getCurrentRealName());
        document.setSize(FileUtils.byteCountToDisplaySize(size));
        document.setFilename(newFileName);
        documentService.save(document);


    }

}
