package com.sparta.greeypeople.menu.service;

import com.sparta.greeypeople.exception.DataNotFoundException;
import com.sparta.greeypeople.menu.dto.response.AdminMenuResponseDto;
import com.sparta.greeypeople.menu.entity.Menu;
import com.sparta.greeypeople.menu.repository.MenuRepository;
import com.sparta.greeypeople.store.entity.Store;
import com.sparta.greeypeople.store.repository.StoreRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public MenuService(StoreRepository storeRepository, MenuRepository menuRepository){
        this.storeRepository = storeRepository;
        this.menuRepository = menuRepository;
    }

    public List<AdminMenuResponseDto> getStoreMenu(Long storeId) {
        findStore(storeId);

        List<Menu> menus = menuRepository.findByStoreId(storeId);
        return menus.stream().map(AdminMenuResponseDto::new).collect(Collectors.toList());
    }

    public Store findStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
            () -> new DataNotFoundException("조회된 가게의 정보가 없습니다.")
        );
    }
}
