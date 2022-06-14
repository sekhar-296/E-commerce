package com.stackroute.shopsListService;

import com.stackroute.shopsListService.exception.NoShopFoundException;
import com.stackroute.shopsListService.exception.ShopAlreadyExistException;
import com.stackroute.shopsListService.model.Address;
import com.stackroute.shopsListService.model.Location;
import com.stackroute.shopsListService.model.Shop;
import com.stackroute.shopsListService.repository.ShopRepository;
import com.stackroute.shopsListService.service.ShopServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    ShopServiceImpl shopService;

    private Shop shop;
    private Shop shop1;
    private Shop shop2;
    private List<Shop> listRm;
    private List<Shop> emptylist;
    private Address address;
    private Location location;


    @BeforeEach
    public void setup()
    {
        byte[] shopImage = new byte[0];
        address =new Address();
        location=new Location();
        shop = new Shop( "abc@gmail.com","Mega Mart", address,location,"4",shopImage,"kunal","8888888888","dfdf");
        shop1 = new Shop( "abcd@gmail.com","Wall Mart", address,location,"4",shopImage,"kunal","8888888888","dfdf");
        shop2 = new Shop( "ab@gmail.com","Green Mart", address,location,"4",shopImage,"kunal","8888888888","dfdf");
        listRm=new ArrayList<>();
        listRm.add(shop);
        listRm.add(shop1);
        listRm.add(shop2);
        emptylist=new ArrayList<>();
    }
    @AfterEach
    public void destroy()
    {
        shop=null;
        shop1=null;
        shop2=null;
        listRm=null;
        emptylist=null;
    }

    @Test
    public void givenShopToSaveReturnShop() throws ShopAlreadyExistException
    {
        when(shopRepository.findById(shop.getEmail())).thenReturn(Optional.ofNullable(null));
        when(shopRepository.save(shop)).thenReturn(shop);

        assertEquals(shop,shopService.saveShop(shop));
        verify(shopRepository,times(1)).findById(shop.getEmail());
        verify(shopRepository,times(1)).save(shop);
    }

    @Test
    public void givenShopToSaveReturnShopFailure() throws ShopAlreadyExistException
    {
        when(shopRepository.findById(shop.getEmail())).thenReturn(Optional.ofNullable(shop));
        assertThrows(ShopAlreadyExistException.class,()->shopService.saveShop(shop));
        verify(shopRepository,times(1)).findById(shop.getEmail());
        verify(shopRepository,times(0)).save(shop);
    }

    @Test
    public void getAllShopsReturnShops() throws NoShopFoundException {

        when(shopRepository.findAll()).thenReturn(listRm);

        assertEquals(3,shopService.getAllShops().size());
        verify(shopRepository,times(2)).findAll();
    }

    @Test
    public void getAllShopsReturnShopsFailure() throws NoShopFoundException {

        when(shopRepository.findAll()).thenReturn(emptylist);
        assertThrows(NoShopFoundException.class,()->shopService.getAllShops());

        verify(shopRepository,times(1)).findAll();
    }

}
