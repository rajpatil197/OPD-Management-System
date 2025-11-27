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
import com.opd_management.Services.MedicineService;
import com.opd_management.dtos.MedicineDto;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.Medicine;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/")
	public ResponseEntity<Medicine>SaveMedicine(@RequestBody MedicineDto medicineDto){
		
		Medicine medicine = new Medicine();
		
		medicine.setMedicine_name(medicineDto.getMedicine_name());
		medicine.setType(medicineDto.getType());
		
		Doctor doctor = doctorService.getDoctorById(medicineDto.getDoctorid());
		medicine.setDoctorid(doctor);
		
		Medicine SaveMedicine = medicineService.saveMedicine(medicine);
		
		return new ResponseEntity<>(SaveMedicine,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Medicine>>GetAllMedicine(){
		
		List<Medicine> medicine = medicineService.GetAllMedicine();
		if(medicine == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(medicine,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medicine>GetMedicineById(@PathVariable("id")int id){
		
		Medicine medicine = medicineService.GetMedicineById(id);
		if(medicine == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(medicine,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medicine>UpdateMedicineById(@PathVariable("id")int id,@RequestBody MedicineDto medicineDto){
		
	Medicine medicine = medicineService.GetMedicineById(id);
	
	if(medicine == null) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	medicine.setMedicine_name(medicineDto.getMedicine_name());
	medicine.setType(medicineDto.getType());
	
	Doctor doctor = doctorService.getDoctorById(medicineDto.getDoctorid());
	medicine.setDoctorid(doctor);
	
	Medicine UpdateMedicine = medicineService.saveMedicine(medicine);
	
	return new ResponseEntity<>(UpdateMedicine,HttpStatus.OK);
	
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<Medicine>DeleteMedicine(@PathVariable("id")int id){
		
		Medicine medicine = medicineService.GetMedicineById(id);
		if(medicine == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		medicineService.DeleteMedicine(id);
		
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
	
}
