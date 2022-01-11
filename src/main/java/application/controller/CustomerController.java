package application.controller;

import application.service.CustomerService;
import application.service.BookService;
import application.service.OrderService;
import application.service.IntToStringService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import application.domain.Customer;

/**
 * Controller class for {@link Customer} services.
 */
@Controller
public class CustomerController {
    private final String INFORMATION = "information";
    private CustomerService customerService;
    private BookService bookService;
    private OrderService orderService;
    private IntToStringService intToStringService;

    public CustomerController(CustomerService customerService, BookService bookService, OrderService orderService, IntToStringService intToStringService) {
        this.customerService = customerService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.intToStringService = intToStringService;
    }

    @RequestMapping("/customerRegistration")
    public String registerForm() {
        return "registration";
    }

    @RequestMapping("/registration")
    public String registerCustomer(@RequestParam(value = "name") String customerName, Model model) {
        customerService.registerCustomer(customerName);
        model.addAttribute("message", customerName + " registered");
        return INFORMATION;
    }

    @RequestMapping("/orderBook")
    public String orderBook(@RequestParam(value = "isbnForOrder") String isbn, Model model) {
        model.addAttribute("message", "Order the following book:");
        model.addAttribute("book", bookService.findBookById(intToStringService.convertToInteger(isbn)));
        return "order-book";
    }

    @RequestMapping("/ordered")
    public String ordered(@RequestParam(value = "ISBNNumber") String isbn,
                          @RequestParam(value = "customerId") String customerId,
                          Model model) {
        orderService.orderBook(intToStringService.convertToInteger(isbn), intToStringService.convertToInteger(customerId));
        model.addAttribute("message", "Customer with ID: "
                                        + customerService.getCustomerById(intToStringService.convertToInteger(customerId)).getCustomerId()
                                        + " taken book");
        return INFORMATION;
    }

    @RequestMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("message", "Registered customers in library");
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customers";
    }

}
