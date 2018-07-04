
public class Polyclinic implements IPolyclinic {
	public Patient[] patients;
	public Doctor[] doctors;
	public Appointment[] appointments;

	public Polyclinic(int maxDoctorsNumber, int maxPatientsNumber, int maxAppointmentsNumber) {
		this.doctors = new Doctor[maxDoctorsNumber];
		this.patients = new Patient[maxPatientsNumber];
		this.appointments = new Appointment[maxAppointmentsNumber];

		System.out.println("Created Polyclinic with "+ 
				maxDoctorsNumber +" places for doctors, "+
				maxPatientsNumber+" places for patients, "+
				maxAppointmentsNumber +" appointments");
	}

	public void setAppointment(Doctor doctor, Patient patient) {
		if (ArrayOperator.hasSpace(this.appointments)) {
			this.appointments[ArrayOperator.getFreePlace(this.appointments)] = new Appointment(doctor, patient);
		}
	}

	@Override
	public void cancelAppointment(Patient patient) {
		for (int i = 0; i < appointments.length; i++) {
			if (appointments[i].patient == patient) {
				appointments[i] = null;
			}
		}
	}

	@Override
	public void addDoctor(Doctor doctor) {
		if (ArrayOperator.hasSpace(this.doctors)) {
			this.doctors[ArrayOperator.getFreePlace(this.doctors)] = doctor;
		}
	}

	@Override
	public void addPatient(Patient patient) {
		if (ArrayOperator.hasSpace(this.patients)) {
			this.patients[ArrayOperator.getFreePlace(this.patients)] = patient;
		}
	}

	@Override

	public int getPatientNumber(Doctor doctor) {
		int result = 0;

		for (int i = 0; i < appointments.length; i++) {
			if (appointments[i].patient != null) {
				if (appointments[i].doctor == doctor)
					result++;
			}
		}
		return result;
	}

	@Override
	public int getPatientCount() {
		int result = 0;

		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null) {
				result++;
			}
		}
		return result;
	}

	@Override
	public int getDoctorCount() {
		int result = 0;

		for (int i = 0; i < doctors.length; i++) {
			if (doctors[i] != null) {
				result++;
			}
		}
		return result;
	}

	public String getDoctors() {
		String result = "Doctors : \n";
		for (int i = 0; i < doctors.length; i++) {
			if (doctors[i] != null) {
				result += doctors[i] + "\n";
			}
		}
		return result;
	}

	public String getPatients() {
		String result = "Patients : \n";
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null) {
				result += patients[i] + "\n";
			}
		}
		return result;
	}

	public String getAppointments() {
		String result = "Appointments : \n";
		for (int i = 0; i < appointments.length; i++) {
			if (appointments[i] != null) {
				result += appointments[i] + "\n";
			}
		}
		return result;
	}

	public String toString() {
		return "Polyclinic:\n" + getDoctors() + "\n" + getPatients() + "\n" + getAppointments();
	}
}
