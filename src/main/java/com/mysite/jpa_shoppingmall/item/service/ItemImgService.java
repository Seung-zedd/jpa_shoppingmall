package com.mysite.jpa_shoppingmall.item.service;

import com.mysite.jpa_shoppingmall.file.FileService;
import com.mysite.jpa_shoppingmall.item.entity.ItemImg;
import com.mysite.jpa_shoppingmall.item.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${item.imgLocation}")
    private String itemImgLocation;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());

            // WebMvcConfig에서 /images/**로 설정 && application-dev.yml에서 uploadPath 경로인 "C:/shop/" 아래 item 폴더에 이미지를 저장
            imgUrl = "/images/item/" + imgName;
        }


        /**
         * 상품 이미지 정보 저장
         * @param oriImgName: 업로드했던 상품 이미지 파일의 원래 이름
         * @param imgName: 실제 로컬에 저장된 상품 이미지 파일의 이름
         * @param imgUrl: 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로
         */
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }
}
