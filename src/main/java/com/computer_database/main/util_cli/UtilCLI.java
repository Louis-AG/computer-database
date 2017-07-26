package com.computer_database.main.util_cli;

import com.computer_database.dto.ComputerDto;
import com.computer_database.mapper.ComputerDtoMapper;
import com.computer_database.model.Company;
import com.computer_database.model.CompanyBuilder;
import com.computer_database.model.Computer;
import com.computer_database.model.ComputerBuilder;
import com.computer_database.service.CompanyService;
import com.computer_database.service.ComputerService;
import com.computer_database.service.ICompanyService;
import com.computer_database.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UtilCLI {

    private static Logger logger = LoggerFactory.getLogger(UtilCLI.class);
    private IComputerService computerService;
    private ICompanyService companyService;
    private Scanner in = new Scanner(System.in);

    /**
     * constructor.
     */
    public UtilCLI() {
        computerService = ComputerService.getInstance();
        companyService = CompanyService.getInstance();
    }

    /**
     * Function run.
     */
    public void run() {

        boolean end = false;
        int choix;

        do {
            showMenu();
            choix = in.nextInt();

            switch (choix) {
                case 1:
                    displayCompanies();
                    break;

                case 2:
                    displayComputersWithPagging();
                    break;

                case 3:
                    addInCLI();
                    break;

                case 4:
                    updateComputerInCLI();
                    break;

                case 5:
                    deleteComputerInCLI();
                    break;

                case 6:
                    displayComputerDetailInCLI();
                    break;

                case 7:
                    deleteCompanyInCLI();
                    break;

                case 0:
                    end = true;
                    break;

                default:
                    System.out.println("Try again :-p");
                    in.reset();
                    break;
            }
            in.reset();
        } while (!end);

        in.close();
        System.out.println("Bye bye !!!");
    }

    /**
     * Display menu screen.
     */
    private void showMenu() {
        System.out.println("Welcome !!!");
        System.out.println("What do you want to do ? ");
        System.out.println("1 - Display companies");
        System.out.println("2 - Display computers");
        System.out.println("3 - Add computer");
        System.out.println("4 - Update computer with id");
        System.out.println("5 - Delete computer with id");
        System.out.println("6 - Show computer details with id");
        System.out.println("7 - Delete company with id");
        System.out.println("0 - Exit");
    }

    /**
     * Display Companies.
     */
    private void displayCompanies() {
        List<Company> companies = companyService.listAll();
        for (Company c : companies) {
            System.out.println(c.toString());
        }
    }

    /**
     * Display Computers.
     */
    private void displayComputersWithPagging() {

        boolean stillIn = true;
        int index = 0;
        in.reset();

        do {
            for (Computer c : computerService.listAllWithPagingAndCompanyName(index, 20, "", "").getDatas()) {
                System.out.println(c.toString());
            }
            System.out.println("1 - to quit, everything  else to display");
            int choix2 = in.nextInt();
            switch (choix2) {
                case 1:
                    stillIn = false;
                    break;
                default:
                    index++;
                    break;
            }
        } while (stillIn);
    }

    /**
     * add in CLI.
     */
    private void addInCLI() {
        Computer c = new ComputerBuilder().createComputer();
        System.out.println("name :");
        in.reset();
        c.setName(in.next());

        try {
            System.out.println("introduced date (yyyy-MM-dd) :");
            c.setIntroduced(LocalDate.parse(in.next()));
        } catch (Exception e) {
            logger.error("Wrong Date !");
        }

        try {
            System.out.println("discontinued date (yyyy-MM-dd) :");
            c.setDiscontinued(LocalDate.parse(in.next()));
        } catch (Exception e) {
            logger.error("Wrong Date !");
        }

        try {
            System.out.println("company id :");
            c.setCompany(new CompanyBuilder().setId(in.nextLong()).createCompany());

        } catch (Exception e) {
            logger.error("Wrong Company ! so default 0 use");
            c.setCompany(new CompanyBuilder().setId(0).createCompany());
        }
        computerService.add(c);
    }

    /**
     * Delete a computer.
     */
    private void deleteComputerInCLI() {
        try {
            System.out.println("Enter the id of the computer to delete :");
            computerService.delete(in.nextLong());
        } catch (Exception e) {
            logger.error("Wrong Computer ! nothing delete");
        }
    }

    /**
     * Display Computer detail.
     */
    private void displayComputerDetailInCLI() {

        try {
            System.out.println("Enter the id of the computer to show detail :");
            ComputerDto computer = ComputerDtoMapper.map(computerService.detail(in.nextLong()));

            System.out.println(computer.toString());

        } catch (Exception e) {
            logger.error("Wrong enter !");
        }
    }

    /**
     * Update a Computer in CLI.
     */
    private void updateComputerInCLI() {
        Computer computer = new ComputerBuilder().createComputer();
        System.out.println("Enter the id of the computer to update :");
        computer = computerService.detail(in.nextLong());

        System.out.println(computer.toString());


        System.out.println("name : (" + computer.getName() + ")");
        in.reset();
        computer.setName(in.next());

        try {
            System.out.println("introduced date (yyyy-MM-dd) : (" + computer.getIntroduced() + ")");
            computer.setIntroduced(LocalDate.parse(in.next()));
        } catch (Exception e) {
            logger.error("Wrong Date !");
        }

        try {
            System.out.println("discontinued date (yyyy-MM-dd) : (" + computer.getDiscontinued() + ")");
            computer.setDiscontinued(LocalDate.parse(in.next()));
        } catch (Exception e) {
            logger.error("Wrong Date !");
        }

        try {
            System.out.println("company id : (" + computer.getCompany().getId() + ")");
            computer.setCompany(new CompanyBuilder().setId(in.nextLong()).createCompany());
        } catch (Exception e) {
            logger.error("Wrong Company ! so default 0 use");
            computer.setCompany(new CompanyBuilder().setId(0).createCompany());

        }

        computerService.update(computer);
    }

    /**
     * Delete a company.
     */
    private void deleteCompanyInCLI() {
        System.out.println("Enter the id of the computer to delete :");
        companyService.deleteCompanyAndComputers(in.nextLong());
    }

}
