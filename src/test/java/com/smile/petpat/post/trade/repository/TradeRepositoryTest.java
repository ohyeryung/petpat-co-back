package com.smile.petpat.post.trade.repository;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.category.repository.TradeCategoryDetailRepository;
import com.smile.petpat.post.common.status.PostStatus;
import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.repository.TradeRepository;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("중고거래 JPA 연결 테스트")
@DataJpaTest()  // transactional 이 걸려있다, 기본값은 rollback이다. 따라서 ddl문이 적용되지 않은다.
class TradeRepositoryTest {

    private final UserRepository userRepository;
    private final TradeRepository tradeRepository;
    private final TradeCategoryDetailRepository tradeCategoryDetailRepository;

    public TradeRepositoryTest(
            @Autowired UserRepository userRepository,
            @Autowired TradeRepository tradeRepository,
            @Autowired TradeCategoryDetailRepository tradeCategoryDetailRepository
    ){
        this.userRepository = userRepository;
        this.tradeRepository=tradeRepository;
        this.tradeCategoryDetailRepository = tradeCategoryDetailRepository;
    }

    private User user1;

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
    @DisplayName("중고거래 게시물 Create 테스트")
    @Test
    void givenTestData_whenCreating_thenWorksFine(){
        // Given
        TradeCategoryDetail tradeCategoryDetail = (tradeCategoryDetailRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("존재하지않는 카테고리 아이디입니다.")
        ));
        long previousCount = tradeRepository.count();
        // When
        Trade trade = Trade
                .builder()
                .user(user1)
                .title("테스트중고거래제목1")
                .content("테스트중고거래내용1")
                .price(1000L)
                .cityName("서울시")
                .cityCountryName("양천구")
                .townShipName("목동")
                .detailAdName("103")
                .fullAdName("서울시 양천구 목동 103")
                .postType(PostType.TRADE)
                .status(PostStatus.TRADE_FINDING)
                .viewCnt(0)
                .tradeCategoryDetail(tradeCategoryDetail)
                .build();
        Trade saveTrade = tradeRepository.save(trade);

        // Then
        System.out.println(saveTrade.getTradeCategoryDetail().getTradeCategoryDetailName());
        assertThat(tradeRepository.count()).isEqualTo(previousCount +1);

    }
    @DisplayName("중고거래 게시물 DELETE 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        // Given

        // When

        // Then


    }

}
