import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currency_converter.Service.CurrencyService;
import com.currency_converter.exception.InvalidCurrencyException;


@RestController
@Controller 
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/rates")
    public ResponseEntity<?> getExchangeRates(@RequestParam String coun){
        try {
            return ResponseEntity.ok(currencyService.fetchExchangeRates(coun));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching exchange rates" + e.getMessage());
        }
    }

    @GetMapping("/") 
    public String showHomePage() {
        return "index";
    }
    
    @PostMapping("/convert")
    public ResponseEntity<?> convertCurrency(@RequestBody Map<String, Object> request) {
        try {
            if (request.get("from") == null || request.get("to") == null || request.get("amount") == null) {
                throw new InvalidCurrencyException("Invalid request data. All fields are required.");
            }
            if (((String) request.get("from")).equalsIgnoreCase((String) request.get("to"))) {
                return ResponseEntity.ok(currencyService.currencyEquals(request));
            }
            return ResponseEntity.ok(currencyService.convertCurrency(request));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error Converting Currency: " + e.getMessage());
        }
    }
}