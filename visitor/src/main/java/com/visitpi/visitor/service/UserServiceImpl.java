package com.visitpi.visitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitpi.visitor.exception.CollectionException;
import com.visitpi.visitor.model.User;
import com.visitpi.visitor.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> getAllUsers(){
		List<User> users = userRepo.findAll();
		if (users.size() > 0) {
			return users;
		}else {
			return new ArrayList<User>();
		}
	}

	@Override
	public User getSingleUser(String id) throws CollectionException{
		Optional<User> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			throw new CollectionException(CollectionException.NotFoundException(id));
		}else {
			return userOptional.get();
		}
	}

	@Override
	public void createUser(User user) throws CollectionException{
		Optional<User> userOptional= userRepo.findByName(user.getName());
        if(userOptional.isPresent())
        {
            throw new CollectionException(CollectionException.UserAlreadyExists());
        }
        else
        {
        	//user.setCreatedAt(new Date(System.currentTimeMillis()));
            userRepo.save(user);
        }
		
	}

	@Override
	public void updateUser(String id, User user) throws CollectionException{
		Optional<User> userWithId = userRepo.findById(id);
        Optional<User> userWithSameName = userRepo.findByName(user.getName());
        if(userWithId.isPresent())
        {
            if(userWithSameName.isPresent() && !userWithSameName.get().getId().equals(id))
            {

                throw new CollectionException(CollectionException.UserAlreadyExists());
            }
            User userToUpdate=userWithId.get();
            
            userToUpdate.setName(user.getName()!=null?user.getName():userToUpdate.getName());
            userToUpdate.setDob(user.getDob()!=null?user.getDob():userToUpdate.getDob());
            userToUpdate.setActive(user.getActive()!=null?user.getActive():userToUpdate.getActive());
            //userToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            userRepo.save(userToUpdate);
        }
        else
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
	}

	@Override
	public void deleteUserById(String id) throws CollectionException{
		Optional<User> userOptional = userRepo.findById(id);
        if(!userOptional.isPresent())
        {
            throw new CollectionException(CollectionException.NotFoundException(id));
        }
        else
        {
            userRepo.deleteById(id);
        }
		
	}

}
