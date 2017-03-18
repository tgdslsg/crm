package com.sus.service;

import com.sus.dao.DocumentDao;
import com.sus.pojo.Document;
import com.sus.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.xml.ws.Action;
import java.util.List;

/**
 * Created by tgdsl on 2017/3/18.
 */
@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    public List<Document> findDocumentByFid(Integer fid) {
        return  documentDao.findDocumentByFid(fid);
    }
    //新建文件夹
    public void saveDir(String name, Integer fid) {
        Document document = new Document();
        document.setName(name);
        document.setFid(fid);
        document.setCreatuser(ShiroUtils.getCurrentRealName());
        document.setType(Document.TYPE_DIR);
        documentDao.save(document);
    }
    //新建文件
    public void save(Document document) {
        documentDao.save(document);
    }
}
