package com.niit.authenticationservice.service;
import com.niit.authenticationservice.domain.Address;
import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;

    }


  @Override
  public User saveUser(User user) throws UserAlreadyExistsException {
      if(userRepository.findById(user.getEmail()).isPresent())
      {
          throw new UserAlreadyExistsException();
      }

        return userRepository.save(user);
    }

  @Override
  public User findByEmailAndPassword(String email,String password) throws UserNotFoundException {
         User user =  userRepository.findByEmailAndPassword(email, password);
         if(user == null){
           throw new UserNotFoundException();
         }
         return user;

  }

    @Override
    public List<User> getAllUsers() {
      return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if(user == null)
        {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) throws UserNotFoundException {
        User user1=userRepository.findById(user.getEmail()).get();
        if(user1==null)
        {
            throw new UserNotFoundException();
        }
        if(userRepository.save(user)!=null){
            return true;

        }
        return false;
    }

    @Override
    public boolean patchUser(User user) throws UserNotFoundException {
        User user1=userRepository.findById(user.getEmail()).get();
        System.out.println(user1);
        if(user1==null)
        {
            throw new UserNotFoundException();
        }
        if(user1!=null){
            user1.setEmail(user.getEmail()) ;
            user1.setUserName(user.getUserName());
            user1.setDob(user.getDob());

            Address address = new Address();
            address.setNationality(user.getAddress().getNationality());
            address.setState(user.getAddress().getState());
            address.setPin(user.getAddress().getPin());
            address.setLandMark(user.getAddress().getLandMark());
            address.setCity(user.getAddress().getCity());
            address.setStreetName(user.getAddress().getStreetName());
//            user1.getAddress().setStreetName(user.getAddress().getStreetName());
//            user1.getAddress().setCity(user.getAddre
            user1.setAddress(address);
            userRepository.save(user1);
            return true;

        }
        return false;
    }

}
