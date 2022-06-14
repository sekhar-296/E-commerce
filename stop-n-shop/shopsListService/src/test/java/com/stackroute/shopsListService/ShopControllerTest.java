package com.stackroute.shopsListService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.shopsListService.controller.ShopsController;
import com.stackroute.shopsListService.exception.NoShopFoundException;
import com.stackroute.shopsListService.exception.ShopAlreadyExistException;
import com.stackroute.shopsListService.model.Address;
import com.stackroute.shopsListService.model.Location;
import com.stackroute.shopsListService.model.Shop;
import com.stackroute.shopsListService.service.ShopService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
public class ShopControllerTest {

    @Mock
    private ShopService shopService;

    @InjectMocks
    ShopsController shopsController;

    @Autowired
    private MockMvc mockMvc;

    private Address address;
    private Location location;
    private Shop shop;
    private Shop shop1;
    private Shop shop2;
    private List<Shop> listRm;
    private List<Shop> emptylist;


    @BeforeEach
    public void setup()
    {
        byte[] shopImage = new byte[1];
        address =new Address();
        location=new Location();
        shop = new Shop( "abc@gmail.com","Mega Mart", address,location,"4",shopImage,"kunal","888","good");
        shop1 = new Shop( "abcd@gmail.com","Wall Mart", address,location,"3",shopImage,"Sharma","999","average");
        shop2 = new Shop( "ab@gmail.com","Green Mart", address,location,"2",shopImage,"naman","666","nice");
        listRm=new ArrayList<>();
        listRm.add(shop);
        listRm.add(shop1);
        listRm.add(shop2);
        emptylist=new ArrayList<>();

        mockMvc = MockMvcBuilders.standaloneSetup(shopsController).build();
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

    private static String convertToJson(final Object obj) {
        String result="";
        try{
            ObjectMapper mapper=new ObjectMapper();
            result=mapper.writeValueAsString(obj);
        }
        catch(JsonProcessingException ex){
            ex.printStackTrace();
            result="JsonProcessingException";
        }
        return result;
    }

//    @Test
//    public void givenShopToSaveReturnsShop() throws Exception
//    {
//        when(shopService.saveShop(any())).thenReturn(shop);
//
//        mockMvc.perform(
//                        post("/myapp/v1/shop")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(convertToJson(shop)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//        verify(shopService,times(1)).saveShop(any());
//    }
//
//    @Test
//    public void givenShopToSaveFailure() throws Exception {
//        when(shopService.saveShop(any())).thenThrow(ShopAlreadyExistException.class);
//
//        mockMvc.perform(
//                        post("/myapp/v1/shop")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(convertToJson(shop)))
//                .andExpect(status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//        verify(shopService,times(1)).saveShop(any());
//    }

    @Test
    public void getAllShopsSuccess() throws Exception {
        when(shopService.getAllShops()).thenReturn(listRm);

        mockMvc.perform(
                        get("/myapp/v1/shop")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(shop)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(shopService,times(1)).getAllShops();
    }
    @Test
    public void getAllShopsFailure() throws Exception {
        when(shopService.getAllShops()).thenThrow(NoShopFoundException.class);

        mockMvc.perform(
                        get("/myapp/v1/shop")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(shop)))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(shopService,times(1)).getAllShops();
    }


}
