package com.example.library.web;

import com.example.library.models.Country;
import com.example.library.service.CountryService;
import com.example.library.web.exceptions.CountryNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countryApi")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = this.countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        try {
            Country _country = this.countryService.save(country.getName(), country.getContinent());
            return new ResponseEntity<Country>(_country, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> findCountryById(@PathVariable("id") long id) {
        try {
            Country country = this.countryService.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
            return new ResponseEntity<Country>(country, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<HttpStatus> deleteCountryById(@PathVariable("id") long id) {
        try {
            this.countryService.deleteCountryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
