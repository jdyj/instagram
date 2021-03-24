package com.example.instagram.service;

import com.example.instagram.domain.Image;
import com.example.instagram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private static final String SAVE_PATH = "/upload";
    private static final String PREFIX_URL = "/upload/";

    @Transactional
    public Long saveFile(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void restore(MultipartFile[] files) {

        for(MultipartFile file : files) {
            try {
                String url;
                Image image = new Image();
                String origFilename = file.getOriginalFilename();
                String extName
                        = origFilename.substring(origFilename.lastIndexOf("."),origFilename.length());
                Long size = file.getSize();

                String saveFilename = saveFileName(extName);

                System.out.println("origFilename = " + origFilename);
                System.out.println("extName = " + extName);
                System.out.println("size = " + size);

                writeFile(file, saveFilename);
                url = PREFIX_URL + saveFilename;
                image.setOrigFilename(origFilename);
                image.setFilePath(url);
                image.setFilename(saveFilename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private String saveFileName(String extName) {
        String fileName = "";
        Calendar calendar = Calendar.getInstance();
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;

        return fileName;
    }

    private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
        boolean result = false;
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
        fos.write(data);
        fos.close();

        return result;
    }


}
