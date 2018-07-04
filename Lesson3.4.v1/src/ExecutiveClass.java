
public class ExecutiveClass {

	public static void main(String[] args) {

		Polyclinic polyclinic = new Polyclinic(5, 7, 6);

		Doctor doctor1 = new Doctor("Dima");
		Doctor doctor2 = new Doctor("Natallia");
		Doctor doctor3 = new Doctor("Oleg");
		Doctor doctor4 = new Doctor("Alex");

		Patient patient1 = new Patient("John");
		Patient patient2 = new Patient("Johnson");
		Patient patient3 = new Patient("Tim");
		Patient patient4 = new Patient("Mike");
		Patient patient5 = new Patient("Denis");
		Patient patient6 = new Patient("Ahmed");
		
		polyclinic.addDoctor(doctor1);
		polyclinic.addDoctor(doctor2);
		polyclinic.addDoctor(doctor3);
		polyclinic.addDoctor(doctor4);
		
		polyclinic.addPatient(patient1);
		polyclinic.addPatient(patient2);
		polyclinic.addPatient(patient3);
		polyclinic.addPatient(patient4);
		polyclinic.addPatient(patient5);
		polyclinic.addPatient(patient6);
		
		polyclinic.setAppointment(doctor1, patient1);
		polyclinic.setAppointment(doctor2, patient2);
		polyclinic.setAppointment(doctor3, patient3);
		polyclinic.setAppointment(doctor2, patient4);
		polyclinic.setAppointment(doctor2, patient5);
		polyclinic.setAppointment(doctor2, patient6);
		
		polyclinic.cancelAppointment(patient2);

		polyclinic.setAppointment(doctor4, patient2);

		System.out.println(polyclinic);
		System.out.println("Polyclinic has " + polyclinic.getDoctorCount() + " doctors.");
		System.out.println("Polyclinic has " + polyclinic.getPatientCount() + " patients.");

	}

}
