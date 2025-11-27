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

import com.opd_management.Services.BillService;
import com.opd_management.Services.VisitService;
import com.opd_management.dtos.BillDto;
import com.opd_management.entities.Bill;
import com.opd_management.entities.Visit;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	private BillService billService;
	
	@Autowired 
	private VisitService visitService;
	
	@PostMapping("/")
	public ResponseEntity<Bill>saveBill(@RequestBody BillDto billDto){
		
		Bill bill = new Bill();
		bill.setConcession(billDto.getConcession());
		bill.setConsultation_fee(billDto.getConsultation_fee());
		bill.setPaid_amount(billDto.getPaid_amount());
		bill.setPayment_status(billDto.getPayment_status());
		bill.setPayment_mode(billDto.getPayment_mode());
		bill.setPending_amount(billDto.getPending_amount());
		bill.setTotal_amount(billDto.getTotal_amount());
		bill.setCreated_at(billDto.getCreated_at());
		
		
		Visit visit = visitService.GetVisitById(billDto.getVisitid());
		bill.setVisitid(visit);
		
		Bill Savebill = billService.saveBill(bill);
	
		return new ResponseEntity<>(Savebill,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Bill>>GetAllBills(){
		
		List<Bill> bill = billService.GetAllBill();
		
		if(bill == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bill,HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bill>GetBillById(@PathVariable("id")int id){
		
		Bill bill = billService.GetBillById(id);
		
		if(bill == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bill,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bill>UpdateBill(@PathVariable("id")int id, @RequestBody BillDto billDto){
		
		Bill bill = billService.GetBillById(id);
		
		if(bill == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		bill.setConcession(billDto.getConcession());
		bill.setConsultation_fee(billDto.getConsultation_fee());
		bill.setPaid_amount(billDto.getPaid_amount());
		bill.setPayment_status(billDto.getPayment_status());
		bill.setPayment_mode(billDto.getPayment_mode());
		bill.setPending_amount(billDto.getPending_amount());
		bill.setTotal_amount(billDto.getTotal_amount());
		bill.setCreated_at(billDto.getCreated_at());
			
		Visit visit = visitService.GetVisitById(billDto.getVisitid());
		bill.setVisitid(visit);
		
		Bill Updatebill = billService.saveBill(bill);
	
		return new ResponseEntity<>(Updatebill,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Bill>DeleteBill(@PathVariable("id")int id){
		
		Bill bill = billService.GetBillById(id);
		
		if(bill == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		billService.DeleteBill(id);
		
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
}
