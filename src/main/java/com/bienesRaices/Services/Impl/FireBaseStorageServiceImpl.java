package com.bienesRaices.Services.Impl;

import com.bienesRaices.Services.FireBaseStorageService;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FireBaseStorageServiceImpl implements FireBaseStorageService {
    private final Path root = Paths.get("uploads");


    @Override
    public String loadImage(MultipartFile localFile, String folder, Long id) {
        try {
            String extension = localFile.getOriginalFilename();
            String fileName = "img" + this.getNumber(id) + extension;

            File file = this.convertToFile(localFile);
            String URL = this.uploadFile(file, folder, fileName);

            file.delete();

            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String uploadFile(File file, String folder, String fileName) throws IOException {
        ClassPathResource json = new ClassPathResource(PATHJSONFILE + File.separator + JSONFILE);
        BlobId blobId = BlobId.of(BUCKETNAME, PATH + "/" + folder + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        Credentials credentials = GoogleCredentials.fromStream(json.getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String url = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS, SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString();
        return url;
    }


    private File convertToFile(MultipartFile localFile) throws IOException {
        File tempFile = File.createTempFile("img", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(localFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getNumber(long id) {
        return String.format("%019d", id);
    }

    @Override
    public boolean delete(String fileName, String folder) {
        ClassPathResource json = new ClassPathResource(PATHJSONFILE + File.separator + JSONFILE);
        BlobId blobId = BlobId.of(BUCKETNAME, PATH + "/" + folder + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        Credentials credentials;
        try {
            credentials = GoogleCredentials.fromStream(json.getInputStream());
        } catch (IOException ex) {
            return false;
        }
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.delete(blobId);
        return true;
    }

}
