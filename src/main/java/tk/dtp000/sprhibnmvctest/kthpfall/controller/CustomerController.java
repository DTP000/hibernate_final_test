package tk.dtp000.sprhibnmvctest.kthpfall.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tk.dtp000.sprhibnmvctest.kthpfall.entity.Customer;
import tk.dtp000.sprhibnmvctest.kthpfall.service.BaseService;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    
	    
	    //SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd mm:ss");
	    //timestampFormat.setLenient(false);
	    //binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(timestampFormat, true));
	    
	    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

	    binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	//private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private BaseService<Customer> customerService;
	
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.getAll();
		theModel.addAttribute("customers", theCustomers);
	
		
		return "list-customers";
	}
	
	@GetMapping("/save")
	public String showFormForAdd(Model theModel) {
		//LOG.debug("inside show customer-form handler method");
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@Valid  @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
            return "customer-form";
        } else {
        	customerService.save(theCustomer);	
    		return "redirect:/customer/list";
        }
		
		
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel)  {
		Customer theCustomer = customerService.getByID(theId);	
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)  {
		customerService.deleteById(theId);
		return "redirect:/customer/list";
	}
}
