package com.computer_database.validator;

import com.computer_database.model.CompanyBuilder;
import com.computer_database.model.Computer;
import com.computer_database.model.ComputerBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;


public class ComputerFormValidator {
    private static final String FIELD_NAME = "name";
    private static final String FIELD_INTRODUCED = "introduced";
    private static final String FIELD_DISCONTINUED = "discontinued";
    private static final String FIELD_COMPANY_ID = "companyId";


    private String results;
    private Map<String, String> errors = new HashMap<String, String>();


    public String getResults() {
        return results;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * @param request request
     * @return Computer
     */
    public Computer validate(HttpServletRequest request) {
        Long uuid = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("computerName");
        String introduced = request.getParameter("computerIntroduced");
        String discontinued = request.getParameter("computerDiscontinued");
        Long companyId = Long.valueOf(request.getParameter("computerCompanyId"));

        ComputerBuilder builder = new ComputerBuilder();

        builder.setId(uuid);
        if (validateComputerName(name)) {
            builder.setName(name);
        }

        LocalDate localDate = validateComputerLocalDate(introduced, FIELD_DISCONTINUED);
        if (localDate != null) {
            builder.setIntroduced(localDate);
        }

        localDate = validateComputerLocalDate(discontinued, FIELD_DISCONTINUED);
        if (localDate != null) {
            builder.setDiscontinued(localDate);
        }

        builder.setCompany(new CompanyBuilder().setId(companyId).createCompany());

        if (errors.isEmpty()) {
            results = "Adding succes.";
        } else {
            results = "Adding failled";
        }

        return builder.createComputer();
    }

    /**
     * @param name name
     * @return bool
     */
    private boolean validateComputerName(String name) {
        if (!(name != null && name.length() < 3)) {
            return true;
        }
        errors.put(FIELD_NAME, "Name should have at least 3 characters.");
        return false;
    }


    /**
     * @param s   string
     * @param key string
     * @return localDate
     * @throws Exception exception
     */
    private LocalDate validateComputerLocalDate(String s, String key) {
        if (s != null && !s.isEmpty()) {
            LocalDate localDate = stringToLocalDate(s);
            if (localDate != null) {
                return localDate;
            }
            errors.put(key, "Date format incorrect, use dd/MM/yyyy.");
        }
        return null;
    }


    /**
     * @param companyId companyId
     * @throws Exception exception
     */
    private void validateComputerCompanyId(String companyId) throws Exception {
        //throw new Exception("This company does not exist.\n");
    }

    /**
     * @param field   field
     * @param message message
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /**
     * @param input input
     * @return LocalDate
     */
    public LocalDate stringToLocalDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }

    }
}
