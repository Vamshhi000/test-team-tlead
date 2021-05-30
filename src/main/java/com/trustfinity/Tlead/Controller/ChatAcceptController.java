package com.trustfinity.Tlead.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trustfinity.Tlead.Service.ChatAcceptService;
import com.trustfinity.Tlead.models.ChatAccept;
import com.trustfinity.Tlead.models.ChatRequest;
import com.trustfinity.Tlead.models.ResponceDto;

@Controller
@RequestMapping(value="/chatAccept")
@CrossOrigin(origins = "*")

public class ChatAcceptController {
	
	@Autowired
	private ChatAcceptService chatAcceptService;
	

	
	@GetMapping(("getAllRequsts/{email}"))
	public ResponseEntity<?> getAllRequsts(@PathVariable String email) {
		
	try {
		List<ChatAccept> chatAcceptList=chatAcceptService.getAllRequests(email);
    	return new ResponseEntity<List<ChatAccept>>(chatAcceptList,HttpStatus.OK);
	} catch (Exception e) {
		ResponceDto responceDto = new ResponceDto();
		responceDto.setMsg("INTERNAL SERVER ERROR");
	  	return new ResponseEntity<ResponceDto>(responceDto,HttpStatus.BAD_REQUEST);
	}
	}
	
	@GetMapping("/cancelAccept/{acceptEmail}")
	public ResponseEntity<?> cancelAccept(@PathVariable String acceptEmail,@RequestParam(name="email") String email){
		System.out.print(acceptEmail);
		List<ChatAccept> chatAcceptList =chatAcceptService.deleteAcceptRequest( acceptEmail,email);
		
    	return new ResponseEntity<List<ChatAccept>>(chatAcceptList,HttpStatus.OK);
	}
	
	

}
