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
import com.opd_management.Services.PatientService;
import com.opd_management.Services.ReferralCenterService;
import com.opd_management.Services.ReferralService;
import com.opd_management.Services.VisitService;
import com.opd_management.dtos.ReferralDto;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.Patient;
import com.opd_management.entities.Referral;
import com.opd_management.entities.ReferralCenter;
import com.opd_management.entities.Visit;

@RestController
@RequestMapping("/referrals")
public class ReferralController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private ReferralCenterService referralCenterService;
	
	@Autowired
	private ReferralService referralService;
	
	@PostMapping("/")
	public ResponseEntity<Referral>SaveReferral(@RequestBody ReferralDto referralDto){
		
		Referral referral = new Referral();
		
		referral.setNote_type(referralDto.getNote_type());
		referral.setReason(referralDto.getReason());
		referral.setDeatils(referralDto.getDeatils());
		referral.setCreated_at(referralDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(referralDto.getDoctorid());
		referral.setDoctorid(doctor);
		
		Patient patient = patientService.getPatientByID(referralDto.getPatientid());
		referral.setPatientid(patient);
		
		ReferralCenter referralCenter = referralCenterService.GetReferralCenterById(referralDto.getReferralCenterid());
		referral.setReferralCenterid(referralCenter);
		
		Visit visit = visitService.GetVisitById(referralDto.getVisitid());
		referral.setVisitid(visit);
		
		Referral SaveReferral = referralService.saveReferral(referral);
		
		return new ResponseEntity<>(SaveReferral,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Referral>>GetAllReferral(){
		
		List<Referral> referral = referralService.GetAllReferral();
		if(referral == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(referral,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Referral>GetReferralById(@PathVariable("id")int id){
		
		Referral referral = referralService.GetReferralById(id);
		
		if(referral == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(referral,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Referral>UpdateReferral(@PathVariable("id")int id,@RequestBody ReferralDto referralDto){
		
		Referral referral = referralService.GetReferralById(id);
		
		if(referral == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		referral.setNote_type(referralDto.getNote_type());
		referral.setReason(referralDto.getReason());
		referral.setDeatils(referralDto.getDeatils());
		referral.setCreated_at(referralDto.getCreated_at());
		
		Doctor doctor = doctorService.getDoctorById(referralDto.getDoctorid());
		referral.setDoctorid(doctor);
		
		Patient patient = patientService.getPatientByID(referralDto.getPatientid());
		referral.setPatientid(patient);
		
		ReferralCenter referralCenter = referralCenterService.GetReferralCenterById(referralDto.getReferralCenterid());
		referral.setReferralCenterid(referralCenter);
		
		Visit visit = visitService.GetVisitById(referralDto.getVisitid());
		referral.setVisitid(visit);
		
		Referral UpdateReferral = referralService.saveReferral(referral);
		
		return new ResponseEntity<>(UpdateReferral,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Referral>DeleteReferral(@PathVariable("id")int id){
		
		Referral referral = referralService.GetReferralById(id);
		
		if(referral == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		referralService.DeleteReferral(id);
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
}
