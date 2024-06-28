package com.smile.petpat.post.common.Address.repository;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.post.category.domain.CategoryGroup;
import com.smile.petpat.post.category.domain.PetCategory;
import com.smile.petpat.post.category.repository.CategoryGroupRepository;
import com.smile.petpat.post.category.repository.PetCategoryRepository;
import com.smile.petpat.post.common.Address.Dto.AddressReqDto;
import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.common.Address.service.AddressService;
import com.smile.petpat.post.rehoming.domain.Rehoming;
import com.smile.petpat.post.rehoming.domain.RehomingCommand;
import com.smile.petpat.post.rehoming.domain.RehomingInfo;
import com.smile.petpat.post.rehoming.repository.RehomingRepository;
import com.smile.petpat.user.domain.User;
import com.smile.petpat.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class getRehomingByAddressRepositoryTest {
    @Autowired
    private RehomingRepository rehomingRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryGroupRepository categoryGroupRepository;
    @Autowired
    private PetCategoryRepository petCategoryRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;
    Pageable pageable = PageRequest.of(0,10);
    private User user;


    Address address1;
    Address address2;

    @BeforeEach
    void setup() {

        user = createUser(1);

        address1 = addressService.getAddress(new AddressReqDto("서울특별시","","마포구","연남동"));
        address2 = addressService.getAddress(new AddressReqDto("서울특별시","","종로구","평창동"));

        CategoryGroup categoryGroup = categoryGroupRepository.findById(1L).get();
        PetCategory petCategory = petCategoryRepository.findById(1L).get();


        createRehomingPosts(user, address1,categoryGroup, petCategory, 5);
        createRehomingPosts(user, address2,categoryGroup, petCategory, 3);
    }

    @Nested
    @DisplayName("Success")
    public class Success{
        @Test
        @DisplayName("주소에 해당하는 분양글 조회 성공")
        void success(){
            List<RehomingInfo> rehomingInfos =
                    addressRepository.getRehomingsByAddress(address1, pageable, user.getUserEmail()).getContent();

            assertEquals(5,rehomingInfos.size());
            for(RehomingInfo rehomingInfo : rehomingInfos){
                assertEquals("서울특별시 마포구 연남동",rehomingInfo.getRegion());
            }
        }
    }

    @Nested
    @DisplayName("Failure")
    public class Failure{

        @Test
        @DisplayName("입력 받은 주소가 유효한 주소가 아닌경우")
        void fail_Address_Not_Exist(){
            AddressReqDto wrongAddress = new AddressReqDto("부산특별시","","마포구","연남동");

            Exception ex = assertThrows(CustomException.class,
                    ()->addressService.getAddress(wrongAddress));

            assertEquals("존재하지 않는 주소입니다.",ex.getMessage());
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

    private void createRehomingPosts(User user, Address address, CategoryGroup categoryGroup, PetCategory petCategory, int num) {
        Rehoming rehoming_TEST;
        for (int i = 0; i < num; i++) {
            rehoming_TEST = Rehoming.builder()
                    .user(user)
                    .title("title_TEST " + i)
                    .content("content_TEST " + i)
                    .petName("petName_TEST " + i)
                    .category(categoryGroup)
                    .type(petCategory)
                    .gender(RehomingCommand.PetGender.BOY)
                    .address(address)
                    .build();

            address.getRehomingList().add(rehomingRepository.save(rehoming_TEST));
        }
    }
}
