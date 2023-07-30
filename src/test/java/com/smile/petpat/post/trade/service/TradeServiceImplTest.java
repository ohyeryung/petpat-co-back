package com.smile.petpat.post.trade.service;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.image.repository.ImageRepository;
import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.category.repository.TradeCategoryDetailRepository;
import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.domain.TradeCommand;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.post.trade.repository.TradeRepository;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class TradeServiceImplTest {

    @Autowired
    private TradeServiceImpl tradeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeCategoryDetailRepository tradeCategoryDetailRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private ImageRepository imageRepository;

    User user1;
    @BeforeEach
    void beforeEach(){
        user1 = User
                .builder()
                .userEmail("test0001@email.com")
                .nickname("닉네임테스트1")
                .password("passwordtest1234@")
                .profileImgPath("http://testUserProfile1.jpg")
                .build();
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        imageRepository.deleteAllInBatch();
        tradeRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();


    }

    @DisplayName("중고거래 게시물을 등록한다.")
    @Test
    void createTest() throws IOException {
        // given
        List<MultipartFile> multipartFileList = getMultipartFiles();
        TradeCommand tradeCommand = initTradeCommand(1L,multipartFileList);

        // when
        Long tradeId = tradeService.registerTrade(tradeCommand, user1);
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );
        List<Image> imageList = imageRepository.findAllByPostIdAndPostTypeOrderByPostId(tradeId, PostType.TRADE);

        // then
        assertThat(trade.getTradeId()).isNotNull();
        assertThat(trade.getTitle()).isEqualTo("제목테스트");
        assertThat(trade.getContent()).isEqualTo("내용 테스트");
        assertThat(imageList.stream().map(Image::getFakeFileName).collect(Collectors.toList())).hasSize(2)
                .containsExactlyInAnyOrder("logo-1.png","logo-2.png");

    }
    @DisplayName("로그인한 유저가 보는 중고거래 목록 화면조회입니다.")
    @Test
    void selectTradeListForLogin(){

    }

    @DisplayName("중고거래 게시물 상세조회 하기")
    @Test
    void selectTradeDetail() throws IOException {
        // given
        List<MultipartFile> multipartFileList = getMultipartFiles();
        TradeCommand tradeCommand = initTradeCommand(1L,multipartFileList);

        // when
        Long tradeId = tradeService.registerTrade(tradeCommand, user1);
        TradeInfo.TradeDetail tradeDetail = tradeService.tradeDetail(tradeId);

        // then
        assertThat(tradeDetail.getTitle()).isEqualTo("제목테스트");
        assertThat(tradeDetail.getContent()).isEqualTo("내용 테스트");
        assertThat(tradeDetail.getImageList()).hasSize(2)
                .containsExactlyInAnyOrder("logo-1.png","logo-2.png");

    }

    private List<MultipartFile> getMultipartFiles() throws IOException {
        return List.of(
                new MockMultipartFile("image", "logo-1.png", "image/png",
                        new FileInputStream(getClass().getResource("/images/logo-1.png").getFile())),
                new MockMultipartFile("image2","logo-2.png", "image/png",
                        new FileInputStream(getClass().getResource("/images/logo-2.png").getFile()))
        );
    }

    private TradeCommand initTradeCommand(Long tradeCategoryDetailId,List<MultipartFile> multipartFileList){

        return TradeCommand.builder()
                .user(user1)
                .title("제목테스트")
                .content("내용 테스트")
                .price(10000L)
                .cityName("서울시")
                .cityCountryName("양천구")
                .townShipName("목동")
                .detailAdName("목동로 230")
                .fullAdName("서울시 양천구 목동로 230")
                .tradeCategoryDetailId(tradeCategoryDetailId)
                .images(multipartFileList)
                .build();
    }

    private TradeCategoryDetail getTradeCategoryDetail(Long categoryId) {
        return tradeCategoryDetailRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 카테고리디테일아이디 입니다.")
        );
    }
}