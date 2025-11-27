package com.opd_management.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opd_management.Services.DoctorService;
import com.opd_management.Services.ReferralCenterService;
import com.opd_management.dtos.ReferralCenterDto;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.ReferralCenter;

@RestController
@RequestMapping("/referralCenters")
public class ReferralCenterController {

	@Autowired
	private ReferralCenterService referralCenterService;
	
	@Autowired
	private DoctorService doctorService;
	
	
	@PostMapping("/")
	public ResponseEntity<ReferralCenter>saveReferralCenter(@RequestBody ReferralCenterDto referralCenterDto){
		
		ReferralCenter referralCenter = new ReferralCenter();
		
		referralCenter.setName(referralCenterDto.getName());
		referralCenter.setContact_info(referralCenterDto.getContact_info());
		referralCenter.setAddress(referralCenterDto.getAddress());
		referralCenter.setType(referralCenterDto.getType());
		referralCenter.setCreated_at(referralCenterDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(referralCenterDto.getDoctorid());
		referralCenter.setDoctorid(doctor);
		
		ReferralCenter SaveReferralCenter = referralCenterService.saveReferralCenter(referralCenter);
		
		return new ResponseEntity<>(SaveReferralCenter,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ReferralCenter>>GetAllReferralCenter(){
		
		List<ReferralCenter> referralCenter = referralCenterService.GetAllReferralCenter();
		
		if(referralCenter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(referralCenter,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReferralCenter>GetReferralCenterById(@PathVariable("id")int id){
		
		ReferralCenter referralCenter = referralCenterService.GetReferralCenterById(id);
		
		if(referralCenter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(referralCenter,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReferralCenter>UpdateReferralCenter(@PathVariable("id")int id,@RequestBody ReferralCenterDto referralCenterDto ){
		
		ReferralCenter referralCenter = referralCenterService.GetReferralCenterById(id);
		
		if(referralCenter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		referralCenter.setName(referralCenterDto.getName());
		referralCenter.setContact_info(referralCenterDto.getContact_info());
		referralCenter.setAddress(referralCenterDto.getAddress());
		referralCenter.setType(referralCenterDto.getType());
		referralCenter.setCreated_at(referralCenterDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(referralCenterDto.getDoctorid());
		referralCenter.setDoctorid(doctor);
		
		ReferralCenter UpdateReferralCenter = referralCenterService.saveReferralCenter(referralCenter);
		
		return new ResponseEntity<>(UpdateReferralCenter,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ReferralCenter>DeleteReferralCenter(@PathVariable("id")int id){
		
		ReferralCenter referralCenter = referralCenterService.GetReferralCenterById(id);
		
		if(referralCenter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		referralCenterService.DeleteReferralCenter(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
}
