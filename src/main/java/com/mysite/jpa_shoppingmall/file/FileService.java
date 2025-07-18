package com.mysite.jpa_shoppingmall.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        //? UUID: 서로 다른 개체들을 구별하기 위해서 이름을 부여할 때 사용
        UUID uuid = UUID.randomUUID(); // 파일명 중복 문제를 해결하기 위해 UUID(Universally Unique Identifier)를 사용
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자명
        String savedFileName = uuid + extension; // UUID + 원래 파일 이름의 확장자
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        //? FileOutputStream: 바이트 단위의 출력을 내보내는 클래스
        // 생성자로 파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림을 만든다.
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName; // 업로드된 파일의 이름을 반환

    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath); // 파일이 저장된 경로를 이용하여 파일 객체를 생성

        if (deleteFile.exists()) {
            if (deleteFile.delete()) {
                log.info("파일을 삭제하였습니다.");
            } else {
                // 파일을 지우려다 실패한 경우
                log.error("파일 삭제에 실패했습니다.");
                throw new IOException("파일 삭제에 실패했습니다: " + filePath);
            }
        } else {
            // 파일이 애초에 존재하지 않는 경우
            log.warn("삭제할 파일이 존재하지 않습니다."); // 에러가 아닌 경고 수준일 수 있음
        }
    }
}
