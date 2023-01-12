package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println();

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Enter the car rental details");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.println("Take out (dd/MM/yyyy HH/mm)");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.println("Return (dd/MM/yyyy HH/mm)");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Enter the price per hour: ");
		double pricePerHour= sc.nextDouble();
		System.out.print("Enter the price per day:");
		double pricePerDay= sc.nextDouble();
		
		RentalServices rentalService = new RentalServices(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice");
		System.out.println("Basic payment: " + cr.getInvoice().getBasicPayment());
		System.out.println("Tax: " + cr.getInvoice().getTax());		
		System.out.println("Total payment: " + cr.getInvoice().gettotalPayment());
		sc.close();
	}

}
