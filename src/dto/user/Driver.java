package dto.user;

import constant.UserType;
import constant.VehicleType;

public class Driver extends User {

    protected String licenseNumber;
    protected String vehicleNumber;
    protected VehicleType vehicleType;

    public Driver(int id, String name) {
        super(id, name, UserType.DRIVER);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
