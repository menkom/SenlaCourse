
public interface IPolyclinic {

	public void setAppointment(Doctor doctor, Patient patient);

	public void cancelAppointment(Patient patient);

	public void addDoctor(Doctor doctor);

	public void addPatient(Patient patient);

	public int getPatientNumber(Doctor doctor);

	public int getPatientCount();

	public int getDoctorCount();

}
