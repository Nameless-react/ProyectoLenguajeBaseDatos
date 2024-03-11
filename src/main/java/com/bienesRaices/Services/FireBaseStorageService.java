package com.bienesRaices.Services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FireBaseStorageService {
    public String loadImage(MultipartFile localFile, String folder, Long id);
    public boolean delete(String fileName, String folder);

    final String BUCKETNAME = "lenguajebasedatos.appspot.com";
    final String PATH = "LenguajesBaseDatos";
    final String PATHJSONFILE = "FireBase";
    final String JSONFILE = "lenguajebasedatos-firebase-adminsdk-jis18-67ab99225f.json";
}
