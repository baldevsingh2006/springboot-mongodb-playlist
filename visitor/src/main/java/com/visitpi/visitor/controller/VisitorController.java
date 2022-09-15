package com.visitpi.visitor.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.visitpi.visitor.exception.CollectionException;
import com.visitpi.visitor.model.User;
import com.visitpi.visitor.repository.UserRepository;
import com.visitpi.visitor.service.UserService;

@RestController
public class VisitorController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/users")
	public ResponseEntity<?> createTodo(@RequestBody User user) {
		try {
			userService.createUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (CollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllTodos() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, users.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(userService.getSingleUser(id), HttpStatus.OK);
		} catch (CollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws CollectionException{
		try{
            userService.deleteUserById(id);
            return new ResponseEntity<>("Successfully deleted user with id "+id, HttpStatus.OK);
        }
        catch (CollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }	
	}
	
	@PutMapping("/users/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody User user)
    {
		try {
            userService.updateUser(id,user);
            return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (CollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/users")
//
//	public ResponseEntity<?> getAllUsers() {
//
//		List<User> users = userRepository.findAll();
//
//		if (users.size() > 0) {
//			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("No Data found !", HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@PostMapping("/users")
//	public ResponseEntity<?> createUser(@RequestBody User user) {
//		try {
//
//			userRepository.save(user);
//
//			return new ResponseEntity<User>(user, HttpStatus.CREATED);
//
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	@GetMapping("/users/{id}")
//	public ResponseEntity<?> getSingleUser(@PathVariable String id) {
//		Optional<User> u = userRepository.findById(id);
//		if (u.isPresent()) {
//			return new ResponseEntity<>(u, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("No data found with this Id:" + id, HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@PutMapping("/users/{id}")
//	public ResponseEntity<?> updateSingleUser(@RequestBody User user,@PathVariable String id) {
//		Optional<User> u = userRepository.findById(id);
//		if (u.isPresent()) {
//			
//			User u1=u.get();
//			
//			u1.setName(user.getName()!=null?user.getName():u1.getName());
//			u1.setDob(user.getDob()!=null?user.getDob():u1.getDob());
//			u1.setActive(user.getActive()!=null?user.getActive():u1.getActive());
//			userRepository.save(u1);
//			return new ResponseEntity<>(u1, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("No data found with this Id:" + id, HttpStatus.NOT_FOUND);
//		}
//	}
//	@DeleteMapping("/users/{id}")
//	public ResponseEntity<?> delteSingleUser(@PathVariable String id) {
//		
//	try{
//		
//		userRepository.deleteById(id);
//			return new ResponseEntity<>("Data deleted id:"+id, HttpStatus.OK);
//		} catch(Exception e) {
//			return new ResponseEntity<>("No data found with this Id:" + id, HttpStatus.NOT_FOUND);
//		}
//	}
//	
	
}
