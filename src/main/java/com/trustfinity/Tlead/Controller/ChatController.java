package com.trustfinity.Tlead.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trustfinity.Tlead.Repository.ChatRepository;
import com.trustfinity.Tlead.Service.ChatService;
import com.trustfinity.Tlead.models.ChatAccept;
import com.trustfinity.Tlead.models.ChatModel;
import com.trustfinity.Tlead.models.ResponceDto;

@Controller
@RequestMapping(value="/chat")
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
	private ChatService chatservice;
	@Autowired
	private ChatRepository chatRepository;

	
		    @PostMapping("saveFriends")
	    public ResponseEntity<?> saveFriends(@RequestBody  ChatAccept chatAccept,@RequestParam("email") String email){

		    	try {
			    	if(chatRepository.findByChatEmailAndUsersEmail(chatAccept.getAcceptEmail(), email)==null) {
				    	chatservice.saveFriends(chatAccept,email);
				    	List<ChatModel> chModel= chatservice.getAllFrnds(email);
				      	return new ResponseEntity<List<ChatModel>>(chModel,HttpStatus.OK);
			    		
			    	}else {
			    		
			    		ResponceDto responcedto= new ResponceDto();
			    		responcedto.setMsg("already Accepted");
			        	return new ResponseEntity<ResponceDto>(responcedto,HttpStatus.OK);
			    	}
				} catch (Exception e) {
					ResponceDto responceDto = new ResponceDto();
					responceDto.setMsg("INTERNAL SERVER ERROR");
				  	return new ResponseEntity<ResponceDto>(responceDto,HttpStatus.BAD_REQUEST);
				}

	    	
	    	
	    }
	    
	    @GetMapping("getfrnds/{email}")
	    public ResponseEntity<?> getAllfrnds(@PathVariable String email){
	    try {	
	    	
	    	List<ChatModel> chModel= chatservice.getAllFrnds(email);
	      	return new ResponseEntity<List<ChatModel>>(chModel,HttpStatus.OK);
	    } catch (Exception e) {
			ResponceDto responceDto = new ResponceDto();
			responceDto.setMsg("INTERNAL SERVER ERROR");
		  	return new ResponseEntity<ResponceDto>(responceDto,HttpStatus.BAD_REQUEST);
		}
	
	    }
	   
	    



}
