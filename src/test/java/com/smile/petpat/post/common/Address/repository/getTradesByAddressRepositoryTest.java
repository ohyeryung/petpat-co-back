package com.smile.petpat.post.common.Address.repository;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.common.response.ErrorCode;
import com.smile.petpat.post.category.domain.TradeCategoryDetail;
import com.smile.petpat.post.category.repository.TradeCategoryDetailRepository;
import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.common.Address.service.AddressService;
import com.smile.petpat.post.trade.domain.Trade;
import com.smile.petpat.post.trade.domain.TradeInfo;
import com.smile.petpat.post.trade.repository.TradeRepository;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class getTradesByAddressRepositoryTest {
    @Autowired private TradeRepository tradeRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private AddressService addressService;
    @Autowired private AddressRepository addressRepository;
    @Autowired private TradeCategoryDetailRepository tradeCategoryDetailRepository;


    private Pageable pageable = PageRequest.of(0,10);
    private User user;
    private Address address1;
    private Address address2;

    @BeforeEach
    void setup(){
        user= createUser(1);

        address1 = addressService.getAddress(new AddressReqDto("서울특별시","","마포구","연남동"));
        address2 = addressService.getAddress(new AddressReqDto("서울특별시","","종로구","평창동"));

        createTradePosts(user,address1,6);
        createTradePosts(user,address2,2);
    }

    @Nested
    @DisplayName("Success")
    public class Success{
        @Test
        @DisplayName("Success")
        void success(){
            List<TradeInfo.TradeList> tradeInfos =
                    addressRepository.getTradesByAddress(address1,pageable, user.getUserEmail()).getContent();

            assertEquals(6,tradeInfos.size());
            for(TradeInfo.TradeList tradeList: tradeInfos){
                assertEquals("서울특별시 마포구 연남동",tradeList.getRegion());
            }
        }
    }

    @Nested
    @DisplayName("Failure")
    public class Failure{
        @Test
        @DisplayName("FAIL_ADDRESS_NOT_EXIST")
        void fail_Address_Not_Exist(){
            AddressReqDto wrongAddress = new AddressReqDto("부산특별시","","마포구","연남동");

            Exception ex = assertThrows(CustomException.class,
                    ()->addressService.getAddress(wrongAddress));

            assertEquals(ErrorCode.ILLEGAL_ADDRESS_NOT_EXIST.getMessage(),
                    ex.getMessage());
        }
    }

    private User createUser(int num) {
        User user = User.builder()
                .userEmail("userEmail_TEST_" + num)
                .nickname("nickname_TEST_" + num)
                .password(passwordEncoder.encode("test11!!"))
                .profileImgPath("TEST.jpg_" + num)
                .loginType(User.loginTypeEnum.NORMAL)
                .build();

        return userRepository.save(user);
    }

    private void createTradePosts(User user,Address address,int num){
        TradeCategoryDetail tradeCategoryDetail = tradeCategoryDetailRepository.getReferenceById(1L);
        for(int i =0; i<num; i++){
            Trade trade = Trade
                    .builder()
                    .user(user)
                    .title("테스트중고거래제목"+i)
                    .content("테스트중고거래내용"+i)
                    .price(1000L)
                    .address(address)
                    .tradeCategoryDetail(tradeCategoryDetail)
                    .build();
            address.getTradelist().add(tradeRepository.save(trade));
        }

    }
}
