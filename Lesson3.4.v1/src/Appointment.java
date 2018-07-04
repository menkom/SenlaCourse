
public class Appointment {
	public Patient patient;
	public Doctor doctor;

	public Appointment(Doctor doctor, Patient patient) {
		this.patient = patient;
		this.doctor = doctor;
		System.out.println(this.getClass().getName() + " created.");
	}

	public String toString() {
		return "Appointment : " + doctor + " - " + patient;
	}

}
